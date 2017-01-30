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
import com.nextech.erp.model.Rawmaterialorderhistory;
import com.nextech.erp.service.RawmaterialorderhistoryService;
import com.nextech.erp.status.UserStatus;

@Controller
@RequestMapping("/rawmaterialorderhistory")
public class RawmaterialorderhistoryController {

	@Autowired
	RawmaterialorderhistoryService rawmaterialorderhistoryService;

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addRawmaterialorderhistory(
			@Valid @RequestBody Rawmaterialorderhistory rawmaterialorderhistory, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			rawmaterialorderhistoryService.addRawmaterialorderhistory(rawmaterialorderhistory);
			return new UserStatus(1, "Rawmaterialorderhistory added Successfully !");
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
	public @ResponseBody Rawmaterialorderhistory getRawmaterialorderhistory(@PathVariable("id") long id) {
		Rawmaterialorderhistory rawmaterialorderhistory = null;
		try {
			rawmaterialorderhistory = rawmaterialorderhistoryService.getRawmaterialorderhistoryById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rawmaterialorderhistory;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateRawmaterialorderhistory(@RequestBody Rawmaterialorderhistory Rawmaterialorderhistory) {
		try {
			rawmaterialorderhistoryService.updateRawmaterialorderhistory(Rawmaterialorderhistory);
			return new UserStatus(1, "Rawmaterialorderhistory update Successfully !");
		} catch (Exception e) {
			e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}

	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Rawmaterialorderhistory> getRawmaterialorderhistory() {

		List<Rawmaterialorderhistory> rawmaterialorderhistoryList = null;
		try {
			rawmaterialorderhistoryList = rawmaterialorderhistoryService.getRawmaterialorderhistoryList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rawmaterialorderhistoryList;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody UserStatus deleteRawmaterialorderhistory(@PathVariable("id") long id) {

		try {
			Rawmaterialorderhistory rawmaterialorderhistory = rawmaterialorderhistoryService.getRawmaterialorderhistoryById(id);
			rawmaterialorderhistory.setIsactive(false);
			rawmaterialorderhistoryService.updateRawmaterialorderhistory(rawmaterialorderhistory);
			return new UserStatus(1, "Rawmaterialorderhistory deleted Successfully !");
		} catch (Exception e) {
			return new UserStatus(0, e.toString());
		}

	}
}
