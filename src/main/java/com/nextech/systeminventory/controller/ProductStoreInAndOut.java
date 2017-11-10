package com.nextech.systeminventory.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nextech.systeminventory.dto.ProductInventoryDTO;
import com.nextech.systeminventory.dto.ProductInvetoryMultipleData;
import com.nextech.systeminventory.model.Productinventory;
import com.nextech.systeminventory.model.Productinventoryhistory;
import com.nextech.systeminventory.model.Productorder;
import com.nextech.systeminventory.model.Productorderassociation;
import com.nextech.systeminventory.model.Status;
import com.nextech.systeminventory.service.ProductinventoryService;
import com.nextech.systeminventory.service.ProductinventoryhistoryService;
import com.nextech.systeminventory.service.ProductorderService;
import com.nextech.systeminventory.service.ProductorderassociationService;
import com.nextech.systeminventory.service.StatusService;
import com.nextech.systeminventory.status.UserStatus;


@Controller
@RequestMapping("/productStoreInOut")
public class ProductStoreInAndOut {

	
	@Autowired
	ProductinventoryService productinventoryService;
	
	@Autowired
	ProductorderassociationService productorderassociationService;
	
	@Autowired
	StatusService statusService;
	
	@Autowired
	ProductorderService productorderService;
	
	@Autowired
	ProductinventoryhistoryService productinventoryhistoryService;
	
	@Autowired
	static Logger logger = Logger.getLogger(ProductStoreInAndOut.class);
	
	private static final int STATUS_PRODUCT_ORDER_INCOMPLETE = 76;
	private static final int STATUS_PRODUCT_ORDER_COMPLETE = 75;
	
	@RequestMapping(value = "/inventoryOut", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus inventoryOut(
			@RequestBody ProductInventoryDTO productInventoryDTO, BindingResult bindingResult,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			for(ProductInvetoryMultipleData productInvetoryMultipleData :productInventoryDTO.getProductInvetoryMultipleDatas()){
				
				addProductInventoryHistory(productInvetoryMultipleData);
				
				updateProductInvetory(productInvetoryMultipleData);
				
				updateProductOrderAssoRemainingQuantity(productInventoryDTO, productInvetoryMultipleData, request, response);
			
				updateProductOrder(productInventoryDTO, request, response);
			}
			return new UserStatus(1, "Product Inventory Store Out added Successfully !");
		} catch (ConstraintViolationException cve) {
			logger.error(cve);
			cve.printStackTrace();
			return new UserStatus(0, cve.getCause().getMessage());
		} catch (PersistenceException pe) {
			logger.error(pe);
			pe.printStackTrace();
			return new UserStatus(0, pe.getCause().getMessage());
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return new UserStatus(0, e.getCause().getMessage());
		}
	}
	
	private void updateProductOrderAssoRemainingQuantity(ProductInventoryDTO productInventoryDTO, ProductInvetoryMultipleData productInvetoryMultipleData,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Productorderassociation productorderassociation = productorderassociationService.getProductorderassociationByProdcutOrderIdandProdcutId(productInventoryDTO.getOrderId(), productInvetoryMultipleData.getProductId().getId());
		if(productorderassociation.getRemainingQuantity()>=productInvetoryMultipleData.getQuantity()){
		productorderassociation.setRemainingQuantity(productorderassociation.getRemainingQuantity() - productInvetoryMultipleData.getQuantity());
		productorderassociationService.updateEntity(productorderassociation);
		}
	}
	
	private void updateProductOrder(ProductInventoryDTO productInventoryDTO,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Productorder productorder = productorderService.getEntityById(Productorder.class, productInventoryDTO.getOrderId());
		productorder.setStatus(statusService.getEntityById(Status.class,getProductOrderStatus(productorder)));
		productorder.setCreatedDate(new Timestamp(new Date().getTime()));
		productorderService.updateEntity(productorder);
	}
	
	private int getProductOrderStatus(Productorder productorder)
			throws Exception {
		boolean isOrderComplete = false;
		List<Productorderassociation> productorderassociationsList = productorderassociationService.getProductorderassociationByOrderId(productorder.getId());
		for (Iterator<Productorderassociation> iterator = productorderassociationsList
				.iterator(); iterator.hasNext();) {
			Productorderassociation productorderassociation = (Productorderassociation) iterator.next();
			if (productorderassociation.getRemainingQuantity() == 0) {
				isOrderComplete = true;
			} else {
				isOrderComplete = false;
				break;
			}
		}
		return isOrderComplete ? STATUS_PRODUCT_ORDER_COMPLETE: STATUS_PRODUCT_ORDER_INCOMPLETE;
	}
	
	private void addProductInventoryHistory(ProductInvetoryMultipleData productInvetoryMultipleData) throws Exception {
		Productinventory productinventory = productinventoryService.getProductinventoryByProductId(productInvetoryMultipleData.getProductId().getId());
		Productinventoryhistory productinventoryhistory = new Productinventoryhistory();
		productinventoryhistory.setProductinventory(productinventory);
		productinventoryhistory.setIsactive(true);
		productinventoryhistory.setBeforequantity(productinventory.getQuantityavailable());
		if(productinventory.getQuantityavailable()>=productInvetoryMultipleData.getQuantity()){
		productinventoryhistory.setAfterquantity((productinventory.getQuantityavailable()-productInvetoryMultipleData.getQuantity()));
		productinventoryhistory.setStatus(statusService.getEntityById(Status.class, 77));
		productinventoryhistoryService.addEntity(productinventoryhistory);
		}
	}
	
	private Productinventory updateProductInvetory(ProductInvetoryMultipleData productInvetoryMultipleData) throws Exception{
		Productinventory productinventory = productinventoryService.getProductinventoryByProductId(productInvetoryMultipleData.getProductId().getId());
		if(productinventory !=null){
			if(productinventory.getQuantityavailable()>=productInvetoryMultipleData.getQuantity()){
		productinventory.setQuantityavailable(productinventory.getQuantityavailable()-productInvetoryMultipleData.getQuantity());
		productinventoryService.updateEntity(productinventory);
			}
		}
		return productinventory;
		
	}
	
	@RequestMapping(value = "/inventoryIn", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus inventoryIn(
			@RequestBody ProductInventoryDTO productInventoryDTO, BindingResult bindingResult,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			if(productInventoryDTO.getProductInvetoryMultipleDatas() !=null&&!productInventoryDTO.getProductInvetoryMultipleDatas().isEmpty()){
			for(ProductInvetoryMultipleData productInvetoryMultipleData :productInventoryDTO.getProductInvetoryMultipleDatas()){
				Productinventory productinventory = productinventoryService.getProductinventoryByProductId(productInvetoryMultipleData.getProductId().getId());
				productinventory.setQuantityavailable(productinventory.getQuantityavailable()+productInvetoryMultipleData.getQuantity());
				productinventoryService.updateEntity(productinventory);
			}
			}
			return new UserStatus(1, "Product Inventory Store In Successfully !");
		} catch (ConstraintViolationException cve) {
			logger.error(cve);
			cve.printStackTrace();
			return new UserStatus(0, cve.getCause().getMessage());
		} catch (PersistenceException pe) {
			logger.error(pe);
			pe.printStackTrace();
			return new UserStatus(0, pe.getCause().getMessage());
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return new UserStatus(0, e.getCause().getMessage());
		}
	}
}
