package com.nextech.erp.controller;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.nextech.erp.model.Productrawmaterialassociation;
import com.nextech.erp.service.ProductRMAssoService;
import com.nextech.erp.status.UserStatus;

@Controller
@RequestMapping("/productRMAsso")
public class ProductRMAssoController {

	@Autowired
	ProductRMAssoService productRMAssoService;

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addProductrawmaterialassociation(
			@Valid @RequestBody Productrawmaterialassociation productrawmaterialassociation,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			if (productRMAssoService.getPRMAssociationByPidRmid(
					productrawmaterialassociation.getProduct().getId(),
					productrawmaterialassociation.getRawmaterial().getId()) == null)
				productRMAssoService
						.addEntity(productrawmaterialassociation);
			else
				return new UserStatus(1,
						"Product raw materialassociation already exists !");
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
			@RequestBody Productrawmaterialassociation productrawmaterialassociation) {
		try {
			productRMAssoService
					.updateEntity(productrawmaterialassociation);
			return new UserStatus(1,
					"Productrawmaterialassociation update Successfully !");
		} catch (Exception e) {
			e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}

	@CrossOrigin(origins = "http://localhost:8080")
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
