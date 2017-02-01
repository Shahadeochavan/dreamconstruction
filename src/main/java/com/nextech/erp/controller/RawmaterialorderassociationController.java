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
import com.nextech.erp.model.Rawmaterialorderassociation;
import com.nextech.erp.service.RawmaterialorderassociationService;
import com.nextech.erp.status.UserStatus;

@Controller
@RequestMapping("/rawmaterialorderassociation")
public class RawmaterialorderassociationController {

	@Autowired
	RawmaterialorderassociationService rawmaterialorderassociationService;

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addRawmaterialorderassociation(
			@Valid @RequestBody Rawmaterialorderassociation rawmaterialorderassociation,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			rawmaterialorderassociationService
					.addRawmaterialorderassociation(rawmaterialorderassociation);
			return new UserStatus(1,
					"Rawmaterialorderassociation added Successfully !");
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
	public @ResponseBody Rawmaterialorderassociation getRawmaterialorderassociation(
			@PathVariable("id") long id) {
		Rawmaterialorderassociation rawmaterialorderassociation = null;
		try {
			rawmaterialorderassociation = rawmaterialorderassociationService
					.getRawmaterialorderassociationById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rawmaterialorderassociation;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateRawmaterialorderassociation(
			@RequestBody Rawmaterialorderassociation rawmaterialorderassociation) {
		try {
			rawmaterialorderassociationService
					.updateRawmaterialorderassociation(rawmaterialorderassociation);
			return new UserStatus(1,
					"Rawmaterialorderassociation update Successfully !");
		} catch (Exception e) {
			e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}

	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Rawmaterialorderassociation> getRawmaterialorderassociation() {

		List<Rawmaterialorderassociation> rawmaterialorderassociationList = null;
		try {
			rawmaterialorderassociationList = rawmaterialorderassociationService
					.getRawmaterialorderassociationList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rawmaterialorderassociationList;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody UserStatus deleteRawmaterialorderassociation(
			@PathVariable("id") long id) {

		try {
			Rawmaterialorderassociation rawmaterialorderassociation = rawmaterialorderassociationService
					.getRawmaterialorderassociationById(id);
			rawmaterialorderassociation.setIsactive(false);
			rawmaterialorderassociationService
					.updateRawmaterialorderassociation(rawmaterialorderassociation);
			return new UserStatus(1,
					"Rawmaterialorderassociation deleted Successfully !");
		} catch (Exception e) {
			return new UserStatus(0, e.toString());
		}

	}
}
