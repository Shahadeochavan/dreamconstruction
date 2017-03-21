package com.nextech.erp.controller;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nextech.erp.model.Productorderassociation;
import com.nextech.erp.service.ProductorderassociationService;
import com.nextech.erp.status.UserStatus;

@Controller
@RequestMapping("/productorderassociation")
public class ProductorderassociationController {

	@Autowired
	ProductorderassociationService productorderassociationService;

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addProductorderassociation(
			@Valid @RequestBody Productorderassociation productorderassociation, BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			productorderassociation.setIsactive(true);
			productorderassociation.setCreatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
			productorderassociation.setUpdatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
			productorderassociationService.addEntity(productorderassociation);
			return new UserStatus(1, "Productorderassociation added Successfully !");
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
	public @ResponseBody Productorderassociation getProductorderassociation(@PathVariable("id") long id) {
		Productorderassociation productorderassociation = null;
		try {
			productorderassociation = productorderassociationService.getEntityById(Productorderassociation.class,id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productorderassociation;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateProductorderassociation(@RequestBody Productorderassociation productorderassociation,HttpServletRequest request,HttpServletResponse response) {
		try {
			productorderassociation.setIsactive(true);
			productorderassociation.setCreatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
			productorderassociation.setUpdatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
			productorderassociationService.updateEntity(productorderassociation);
			return new UserStatus(1, "Productorderassociation update Successfully !");
		} catch (Exception e) {
			e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Productorderassociation> getProductorderassociation() {

		List<Productorderassociation> productorderassociationList = null;
		try {
			productorderassociationList = productorderassociationService.getEntityList(Productorderassociation.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return productorderassociationList;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody UserStatus deleteProductorderassociation(@PathVariable("id") long id) {

		try {
			Productorderassociation productorderassociation = productorderassociationService.getEntityById(Productorderassociation.class,id);
			productorderassociation.setIsactive(false);
			productorderassociationService.updateEntity(productorderassociation);
			return new UserStatus(1, "Productorderassociation deleted Successfully !");
		} catch (Exception e) {
			return new UserStatus(0, e.toString());
		}

	}
}
