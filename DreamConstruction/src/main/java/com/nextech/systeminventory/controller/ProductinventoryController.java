package com.nextech.systeminventory.controller;

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

import com.nextech.systeminventory.constants.ERPConstants;
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
	

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addProductinventory(
			@Valid @RequestBody Productinventory productinventory, BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			if (productinventoryService.getProductinventoryByProductId(
				productinventory.getProduct().getId()) == null){
				productinventory.setCreatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
				productinventory.setIsactive(true);
				productinventoryService.addEntity(productinventory);
			}	
			else
				return new UserStatus(0, messageSource.getMessage(
						ERPConstants.PRODUCT_INVENTORY_ASSO_EXIT, null, null));
			return new UserStatus(1, "Productinventory added Successfully !");
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
	public @ResponseBody UserStatus updateProductinventory(@RequestBody Productinventory productinventory,HttpServletRequest request,HttpServletResponse response) {
		try {
			productinventory.setIsactive(true);
			productinventoryService.updateEntity(productinventory);
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
				return new Response(1, "Product Inventory is empty",
						productinventoryList);
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
}