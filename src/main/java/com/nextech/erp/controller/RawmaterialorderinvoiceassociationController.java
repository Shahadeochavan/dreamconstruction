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

import com.nextech.erp.model.Rawmaterialorderinvoiceassociation;
import com.nextech.erp.service.RawmaterialorderinvoiceassociationService;
import com.nextech.erp.status.UserStatus;

@Controller
@RequestMapping("/rawmaterialorderinvoiceassociation")
public class RawmaterialorderinvoiceassociationController {

	@Autowired
	RawmaterialorderinvoiceassociationService rawmaterialorderinvoiceassociationservice;

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addRawmaterialorderinvoiceassociation(
			@Valid @RequestBody Rawmaterialorderinvoiceassociation Rawmaterialorderinvoiceassociation,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError().getDefaultMessage());
			}
			rawmaterialorderinvoiceassociationservice
					.addRawmaterialorderinvoiceassociation(Rawmaterialorderinvoiceassociation);
			return new UserStatus(1, "Rawmaterialorderinvoiceassociation added Successfully !");
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
	public @ResponseBody Rawmaterialorderinvoiceassociation getRawmaterialorderinvoiceassociation(
			@PathVariable("id") long id) {
		Rawmaterialorderinvoiceassociation Rawmaterialorderinvoiceassociation = null;
		try {
			Rawmaterialorderinvoiceassociation = rawmaterialorderinvoiceassociationservice
					.getRawmaterialorderinvoiceassociationById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Rawmaterialorderinvoiceassociation;
	}
}