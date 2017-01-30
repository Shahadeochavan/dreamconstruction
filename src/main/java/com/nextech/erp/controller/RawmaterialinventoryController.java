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

import com.nextech.erp.model.Rawmaterialinventory;
import com.nextech.erp.service.RawmaterialinventoryService;
import com.nextech.erp.status.UserStatus;

@Controller
@RequestMapping("/rawmaterialinventory")
public class RawmaterialinventoryController {



	@Autowired
	RawmaterialinventoryService rawmaterialinventoryService;

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addRawmaterialinventory(
			@Valid @RequestBody Rawmaterialinventory Rawmaterialinventory, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			rawmaterialinventoryService.addRawmaterialinventory(Rawmaterialinventory);
			return new UserStatus(1, "Rawmaterialinventory added Successfully !");
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
	public @ResponseBody Rawmaterialinventory getRawmaterialinventory(@PathVariable("id") long id) {
		Rawmaterialinventory Rawmaterialinventory = null;
		try {
			Rawmaterialinventory = rawmaterialinventoryService.getRawmaterialinventoryById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Rawmaterialinventory;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateRawmaterialinventory(@RequestBody Rawmaterialinventory Rawmaterialinventory) {
		try {
			rawmaterialinventoryService.updateRawmaterialinventory(Rawmaterialinventory);
			return new UserStatus(1, "Rawmaterialinventory update Successfully !");
		} catch (Exception e) {
			e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}

	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Rawmaterialinventory> getRawmaterialinventory() {

		List<Rawmaterialinventory> RawmaterialinventoryList = null;
		try {
			RawmaterialinventoryList = rawmaterialinventoryService.getRawmaterialinventoryList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return RawmaterialinventoryList;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody UserStatus deleteRawmaterialinventory(@PathVariable("id") long id) {

		try {
			Rawmaterialinventory Rawmaterialinventory = rawmaterialinventoryService.getRawmaterialinventoryById(id);
			Rawmaterialinventory.setIsactive(false);
			rawmaterialinventoryService.updateRawmaterialinventory(Rawmaterialinventory);
			return new UserStatus(1, "Rawmaterialinventory deleted Successfully !");
		} catch (Exception e) {
			return new UserStatus(0, e.toString());
		}

	}
}
