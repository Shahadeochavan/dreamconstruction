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

import com.nextech.erp.model.Rawmaterialinventoryhistory;
import com.nextech.erp.service.RawmaterialinventoryhistoryService;
import com.nextech.erp.status.UserStatus;

@Controller
@RequestMapping("/rawmaterialinventoryhistory")
public class RawmaterialinventoryhistoryController {

	@Autowired
	RawmaterialinventoryhistoryService rawmaterialinventoryhistoryService;

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addRawmaterialinventoryhistory(
			@Valid @RequestBody Rawmaterialinventoryhistory rawmaterialinventoryhistory,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			rawmaterialinventoryhistoryService
					.addRawmaterialinventoryhistory(rawmaterialinventoryhistory);
			return new UserStatus(1,
					"Rawmaterialinventoryhistory added Successfully !");
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
	public @ResponseBody Rawmaterialinventoryhistory getRawmaterialinventoryhistory(
			@PathVariable("id") long id) {
		Rawmaterialinventoryhistory rawmaterialinventoryhistory = null;
		try {
			rawmaterialinventoryhistory = rawmaterialinventoryhistoryService
					.getRawmaterialinventoryhistoryById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rawmaterialinventoryhistory;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateRawmaterialinventoryhistory(
			@RequestBody Rawmaterialinventoryhistory rawmaterialinventoryhistory) {
		try {
			rawmaterialinventoryhistoryService
					.updateRawmaterialinventoryhistory(rawmaterialinventoryhistory);
			return new UserStatus(1,
					"Rawmaterialinventoryhistory update Successfully !");
		} catch (Exception e) {
			e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}

	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Rawmaterialinventoryhistory> getRawmaterialinventoryhistory() {

		List<Rawmaterialinventoryhistory> rawmaterialinventoryhistoryList = null;
		try {
			rawmaterialinventoryhistoryList = rawmaterialinventoryhistoryService
					.getRawmaterialinventoryhistoryList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rawmaterialinventoryhistoryList;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody UserStatus deleteRawmaterialinventoryhistory(
			@PathVariable("id") long id) {

		try {
			Rawmaterialinventoryhistory rawmaterialinventoryhistory = rawmaterialinventoryhistoryService
					.getRawmaterialinventoryhistoryById(id);
			rawmaterialinventoryhistory.setIsactive(false);
			rawmaterialinventoryhistoryService
					.updateRawmaterialinventoryhistory(rawmaterialinventoryhistory);
			return new UserStatus(1,
					"Rawmaterialinventoryhistory deleted Successfully !");
		} catch (Exception e) {
			return new UserStatus(0, e.toString());
		}

	}
}
