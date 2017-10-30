package com.nextech.systeminventory.controller;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
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

import com.nextech.systeminventory.constants.ERPConstants;
import com.nextech.systeminventory.dto.ProductInventoryDTO;
import com.nextech.systeminventory.factory.ProductInventoryRequestResponseFactory;
import com.nextech.systeminventory.model.Productinventory;
import com.nextech.systeminventory.service.ProductService;
import com.nextech.systeminventory.service.ProductinventoryService;
import com.nextech.systeminventory.service.UserService;
import com.nextech.systeminventory.status.Response;
import com.nextech.systeminventory.status.UserStatus;
@Controller
@RequestMapping("/productinventory")
public class ProductinventoryController {


	@Autowired
	ProductinventoryService productinventoryService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	static Logger logger = Logger.getLogger(ProductinventoryController.class);
	

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addProductinventory(
			@Valid @RequestBody ProductInventoryDTO productInventoryDTO, BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			if (productinventoryService.getProductinventoryByProductId(
					productInventoryDTO.getProductId()) == null){
				productinventoryService.addEntity(ProductInventoryRequestResponseFactory.setProductInventory(productInventoryDTO, request));
			}	
			else
				return new UserStatus(0, messageSource.getMessage(ERPConstants.PRODUCT_INVENTORY_ASSO_EXIT, null, null));
			return new UserStatus(1, "Productinventory added Successfully !");
			
		} catch (ConstraintViolationException cve) {
			logger.error("Inside ConstraintViolationException");
			System.out.println("Inside ConstraintViolationException");
			cve.printStackTrace();
			return new UserStatus(0, cve.getCause().getMessage());
		} catch (PersistenceException pe) {
			logger.error("Inside PersistenceException");
			pe.printStackTrace();
			return new UserStatus(0, pe.getCause().getMessage());
		} catch (Exception e) {
			logger.error("Inside Exception");
			e.printStackTrace();
			return new UserStatus(0, e.getCause().getMessage());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Productinventory getProductinventory(@PathVariable("id") long id) {
		Productinventory productinventory = null;
		try {
			productinventory = productinventoryService.getEntityById(Productinventory.class,id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productinventory;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateProductinventory(@RequestBody ProductInventoryDTO productInventoryDTO,HttpServletRequest request,HttpServletResponse response) {
		try {
			productinventoryService.updateEntity(ProductInventoryRequestResponseFactory.setProductInventoryUpdate(productInventoryDTO, request));
			return new UserStatus(1, "Productinventory update Successfully !");
		} catch (Exception e) {
			e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Response getProductinventory() {

		List<Productinventory> productinventoryList = null;
		try {
			productinventoryList = productinventoryService.getEntityList(Productinventory.class);
			if (productinventoryList.isEmpty()) {
				return new Response(1, "Product Inventory is empty",productinventoryList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(1, productinventoryList);
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody UserStatus deleteProductinventory(@PathVariable("id") long id) {

		try {
			Productinventory productinventory = productinventoryService.getEntityById(Productinventory.class,id);
			productinventory.setIsactive(false);
			productinventoryService.updateEntity(productinventory);
			return new UserStatus(1, "Productinventory deleted Successfully !");
		} catch (Exception e) {
			return new UserStatus(0, e.toString());
		}
	}
	
	@RequestMapping(value = "/productId/{productId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Productinventory getProductinventoryByProductId(@PathVariable("productId") long productId) {
		Productinventory productinventory = null;
		try {
			productinventory = productinventoryService.getProductinventoryByProductId(productId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productinventory;
	}
}