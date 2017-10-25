package com.nextech.systeminventory.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.nextech.systeminventory.model.Productorder;
import com.nextech.systeminventory.model.Productorderassociation;
import com.nextech.systeminventory.model.Status;
import com.nextech.systeminventory.service.ProductinventoryService;
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
	
	private static final int STATUS_PRODUCT_ORDER_INCOMPLETE = 76;
	private static final int STATUS_PRODUCT_ORDER_COMPLETE = 75;
	
	@RequestMapping(value = "/inventoryIn", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus inventoryIn(
			@RequestBody ProductInventoryDTO productInventoryDTO, BindingResult bindingResult,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			for(ProductInvetoryMultipleData productInvetoryMultipleData :productInventoryDTO.getProductInvetoryMultipleDatas()){
				Productinventory productinventory = productinventoryService.getProductinventoryByProductId(productInvetoryMultipleData.getProductId().getId());
				if(productinventory !=null){
					
				productinventory.setQuantityavailable(productInvetoryMultipleData.getQuantity()+productinventory.getQuantityavailable());
				productinventoryService.updateEntity(productinventory);
				
				updateProductOrderAssoRemainingQuantity(productInventoryDTO, productInvetoryMultipleData, request, response);
				
				updateProductOrder(productInventoryDTO, request, response);
			}else{
				return new UserStatus(1,"please check product inventory for this product ");
			}
				
			}
			return new UserStatus(1, "Product Inventory Store In added Successfully !");
		} catch (ConstraintViolationException cve) {
			System.out.println("Inside ConstraintViolationException");
			cve.printStackTrace();
			return new UserStatus(0, cve.getCause().getMessage());
		} catch (PersistenceException pe) {
			System.out.println("Inside PersistenceException");
			pe.printStackTrace();
			return new UserStatus(0, pe.getCause().getMessage());
		} catch (Exception e) {
			System.out.println("Inside Exception");
			e.printStackTrace();
			return new UserStatus(0, e.getCause().getMessage());
		}
	}
	
	private void updateProductOrderAssoRemainingQuantity(ProductInventoryDTO productInventoryDTO, ProductInvetoryMultipleData productInvetoryMultipleData,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Productorderassociation productorderassociation = productorderassociationService.getProductorderassociationByProdcutOrderIdandProdcutId(
				productInventoryDTO.getOrderId(), productInvetoryMultipleData.getProductId().getId());
		productorderassociation.setRemainingQuantity(productorderassociation.getRemainingQuantity() - productInvetoryMultipleData.getQuantity());
		productorderassociationService.updateEntity(productorderassociation);
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
				Productinventory productinventory = productinventoryService.getProductinventoryByProductId(productInvetoryMultipleData.getProductId().getId());
				if(productinventory.getQuantityavailable()>=productInvetoryMultipleData.getQuantity()){
				productinventory.setQuantityavailable(productinventory.getQuantityavailable()-productInvetoryMultipleData.getQuantity());
				productinventoryService.updateEntity(productinventory);
				}
			}
			return new UserStatus(1, "Product Inventory Store Out Successfully !");
		} catch (ConstraintViolationException cve) {
			System.out.println("Inside ConstraintViolationException");
			cve.printStackTrace();
			return new UserStatus(0, cve.getCause().getMessage());
		} catch (PersistenceException pe) {
			System.out.println("Inside PersistenceException");
			pe.printStackTrace();
			return new UserStatus(0, pe.getCause().getMessage());
		} catch (Exception e) {
			System.out.println("Inside Exception");
			e.printStackTrace();
			return new UserStatus(0, e.getCause().getMessage());
		}
	}
}
