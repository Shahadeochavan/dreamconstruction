package com.nextech.erp.controller;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nextech.erp.constants.ERPConstants;
import com.nextech.erp.model.Product;
import com.nextech.erp.model.Productinventory;
import com.nextech.erp.model.Productinventoryhistory;
import com.nextech.erp.model.Productionplanning;
import com.nextech.erp.model.Productorder;
import com.nextech.erp.model.Productquality;
import com.nextech.erp.model.Status;
import com.nextech.erp.service.ProductService;
import com.nextech.erp.service.ProductinventoryService;
import com.nextech.erp.service.ProductinventoryhistoryService;
import com.nextech.erp.service.ProductionplanningService;
import com.nextech.erp.service.ProductorderService;
import com.nextech.erp.service.ProductorderassociationService;
import com.nextech.erp.service.ProductqualityService;
import com.nextech.erp.service.StatusService;
import com.nextech.erp.status.UserStatus;


@Controller
@RequestMapping("/productquality")
public class ProductqualityController {
	@Autowired
	ProductqualityService productqualityService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	ProductinventoryService productinventoryService;
	
	@Autowired
	ProductinventoryhistoryService productinventoryhistoryService;
	
	@Autowired
	ProductService productService;
	@Autowired
	ProductionplanningService productionplanningService;
	
	@Autowired
	StatusService statusService;
	
	@Autowired
	ProductorderassociationService productorderassociationService;
	
	@Autowired
	ProductorderService productorderService;
	
	
	//private static final int STATUS_PRODUCT__INVENTORY_ADD=25;
	
	@RequestMapping(value = "/productQualityCheck", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addProductquality(@Valid @RequestBody Productquality productqualityInput,
			BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			Productionplanning productionplanningNew = productionplanningService.getEntityById(Productionplanning.class,productqualityInput.getProductionplanning().getId());
			Product product =  productService.getEntityById(Product.class, productionplanningNew.getProduct().getId());
			Productquality productquality = new Productquality();
			productquality.setProductionplanning(productionplanningNew);
			productquality.setCheckQuantity(productqualityInput.getCheckQuantity());
			productquality.setGoodQuantity(productqualityInput.getGoodQuantity());
			productquality.setRejectedQuantity(productqualityInput.getRejectedQuantity());
			productquality.setCreatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
			productquality.setRemark(productqualityInput.getRemark());
			productquality.setProduct(product);
			productquality.setIsactive(true);
			productqualityService.addEntity(productquality);
			//TODO add product inventory history
			addProductInventoryHistory(productquality.getGoodQuantity(), product, request, response);
			//TODO update product inventory
			updateProductInventory(productquality, product, request, response);
			
//			productionplanningNew.
//			updateProductOrder(productorder);
			
			
			
			
			return new UserStatus(1, "Productquality added Successfully !");
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

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Productquality getProductquality(@PathVariable("id") long id) {
		Productquality productquality = null;
		try {
			
			productquality = productqualityService.getEntityById(Productquality.class,id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productquality;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateProductquality(@RequestBody Productquality productquality,HttpServletRequest request,HttpServletResponse response) {
		try {
			productquality.setIsactive(true);
			productquality.setUpdatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
			productqualityService.updateEntity(productquality);
			return new UserStatus(1, "Productquality update Successfully !");
		} catch (Exception e) {
			e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Productquality> getProductquality() {

		List<Productquality> productqualityList = null;
		try {
			productqualityList = productqualityService.getEntityList(Productquality.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return productqualityList;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody UserStatus deleteProductquality(@PathVariable("id") long id) {

		try {
			Productquality productquality = productqualityService.getEntityById(Productquality.class,id);
			productquality.setIsactive(false);
			productqualityService.updateEntity(productquality);
			return new UserStatus(1, "Productquality deleted Successfully !");
		} catch (Exception e) {
			return new UserStatus(0, e.toString());
		}

	}
	private Productinventory updateProductInventory(Productquality productquality,Product product,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Productinventory productinventory =  productinventoryService.getProductinventoryByProductId(productquality.getProduct().getId());
		if(productinventory == null){
			productinventory = new Productinventory();
			productinventory.setProduct(product);
			productinventory.setQuantityavailable(productquality.getGoodQuantity());
			productinventory.setCreatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
			productinventory.setUpdatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
			productinventory.setIsactive(true);
			productinventoryService.addEntity(productinventory);
		}else{
			productinventory.setCreatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
			productinventory.setUpdatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
			productinventory.setQuantityavailable(productinventory.getQuantityavailable() + productquality.getRejectedQuantity());
			productinventoryService.updateEntity(productinventory);
		}
		return productinventory;
	}
	private void addProductInventoryHistory(long goodQuantity,Product product,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Productinventory productinventory =  productinventoryService.getProductinventoryByProductId(product.getId());
		if(productinventory == null){
			productinventory = new Productinventory();
			productinventory.setProduct(product);
			productinventory.setQuantityavailable(0);
			productinventory.setIsactive(true);
			productinventoryService.addEntity(productinventory);
		}
		Productinventoryhistory productinventoryhistory = new Productinventoryhistory();
		productinventoryhistory.setProductinventory(productinventory);
		productinventory.setCreatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
		productinventory.setUpdatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
		productinventoryhistory.setIsactive(true);
		productinventoryhistory.setBeforequantity((int)productinventory.getQuantityavailable());
		productinventoryhistory.setAfterquantity((int)(goodQuantity+productinventory.getQuantityavailable()));
		productinventoryhistory.setStatus(statusService.getEntityById(Status.class, Long.parseLong(messageSource.getMessage(ERPConstants.STATUS_RAW_MATERIAL_INVENTORY_ADD, null, null))));
		productinventoryhistoryService.addEntity(productinventoryhistory);
		
	}
	private void updateProductOrder(Productorder productorder) throws Exception{
		productorder.setStatus(statusService.getEntityById(Status.class, 12));
			productorderService.updateEntity(productorder);
		}

}

