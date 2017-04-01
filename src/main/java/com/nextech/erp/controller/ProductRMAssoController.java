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
import com.nextech.erp.model.Productrawmaterialassociation;
import com.nextech.erp.service.ProductRMAssoService;
import com.nextech.erp.status.UserStatus;

@Controller
@RequestMapping("/productRMAsso")
public class ProductRMAssoController {

	@Autowired
	ProductRMAssoService productRMAssoService;
	
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addProductrawmaterialassociation(
			@Valid @RequestBody Productrawmaterialassociation productrawmaterialassociation,
			BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			if (productRMAssoService.getPRMAssociationByPidRmid(
					productrawmaterialassociation.getProduct().getId(),
					productrawmaterialassociation.getRawmaterial().getId()) == null){
				productrawmaterialassociation.setCreatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
				productrawmaterialassociation.setIsactive(true);
				productRMAssoService
						.addEntity(productrawmaterialassociation);
			}
			else
				return new UserStatus(1,
						messageSource.getMessage(ERPConstants.PRODUCT_RM_ASSO_EXIT, null, null));
			return new UserStatus(1,
					"Productrawmaterialassociation added Successfully !");
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
	public @ResponseBody Productrawmaterialassociation getProductrawmaterialassociation(
			@PathVariable("id") long id) {
		Productrawmaterialassociation productrawmaterialassociation = null;
		try {
			productrawmaterialassociation = productRMAssoService
					.getEntityById(Productrawmaterialassociation.class,id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productrawmaterialassociation;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateProductrawmaterialassociation(
			@RequestBody Productrawmaterialassociation productrawmaterialassociation,HttpServletRequest request,HttpServletResponse response) {
		try {
			productrawmaterialassociation.setUpdatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
			productrawmaterialassociation.setIsactive(true);
			productRMAssoService
					.updateEntity(productrawmaterialassociation);
			return new UserStatus(1,
					"Productrawmaterialassociation update Successfully !");
		} catch (Exception e) {
			e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Productrawmaterialassociation> getProductrawmaterialassociation() {

		List<Productrawmaterialassociation> productrawmaterialassociationList = null;
		try {
			productrawmaterialassociationList = productRMAssoService
					.getEntityList(Productrawmaterialassociation.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return productrawmaterialassociationList;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody UserStatus deleteProductrawmaterialassociation(
			@PathVariable("id") long id) {

		try {
			Productrawmaterialassociation productrawmaterialassociation = productRMAssoService
					.getEntityById(Productrawmaterialassociation.class, id);
			productrawmaterialassociation.setIsactive(false);
			productRMAssoService
					.updateEntity(productrawmaterialassociation);
			return new UserStatus(1,
					"Productrawmaterialassociation deleted Successfully !");
		} catch (Exception e) {
			return new UserStatus(0, e.toString());
		}

	}
}
