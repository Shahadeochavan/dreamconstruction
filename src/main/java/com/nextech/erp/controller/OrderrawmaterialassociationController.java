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
import com.nextech.erp.model.Orderrawmaterialassociation;
import com.nextech.erp.service.OrderrawmaterialassociationService;
import com.nextech.erp.status.UserStatus;

@Controller
@RequestMapping("/orderrawmaterialassociation")
public class OrderrawmaterialassociationController {

	@Autowired
	OrderrawmaterialassociationService orderrawmaterialassociationService;

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addOrderrawmaterialassociation(
			@Valid @RequestBody Orderrawmaterialassociation orderrawmaterialassociation,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			orderrawmaterialassociationService
					.addOrderrawmaterialassociation(orderrawmaterialassociation);
			return new UserStatus(1,
					"Orderrawmaterialassociation added Successfully !");
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
	public @ResponseBody Orderrawmaterialassociation getOrderrawmaterialassociation(
			@PathVariable("id") long id) {
		Orderrawmaterialassociation orderrawmaterialassociation = null;
		try {
			orderrawmaterialassociation = orderrawmaterialassociationService
					.getOrderrawmaterialassociationById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderrawmaterialassociation;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateOrderrawmaterialassociation(
			@RequestBody Orderrawmaterialassociation orderrawmaterialassociation) {
		try {
			orderrawmaterialassociationService
					.updateOrderrawmaterialassociation(orderrawmaterialassociation);
			return new UserStatus(1,
					"Orderrawmaterialassociation update Successfully !");
		} catch (Exception e) {
			e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}

	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Orderrawmaterialassociation> getOrderrawmaterialassociation() {

		List<Orderrawmaterialassociation> orderrawmaterialassociationList = null;
		try {
			orderrawmaterialassociationList = orderrawmaterialassociationService
					.getOrderrawmaterialassociationList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return orderrawmaterialassociationList;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody UserStatus deleteOrderrawmaterialassociation(
			@PathVariable("id") long id) {

		try {
			Orderrawmaterialassociation orderrawmaterialassociation = orderrawmaterialassociationService
					.getOrderrawmaterialassociationById(id);
			orderrawmaterialassociation.setIsactive(false);
			orderrawmaterialassociationService
					.updateOrderrawmaterialassociation(orderrawmaterialassociation);
			return new UserStatus(1,
					"Orderrawmaterialassociation deleted Successfully !");
		} catch (Exception e) {
			return new UserStatus(0, e.toString());
		}

	}
}
