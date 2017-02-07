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
import com.nextech.erp.model.Rmorderinvoiceintakquantity;
import com.nextech.erp.service.RmorderinvoiceintakquantityService;
import com.nextech.erp.status.UserStatus;
@Controller
@RequestMapping("/rmorderinvoiceintakquantity")
public class RmorderinvoiceintakquantityController {

	@Autowired
	RmorderinvoiceintakquantityService rmorderinvoiceintakquantityservice;

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addRmorderinvoiceintakquantity(@Valid @RequestBody Rmorderinvoiceintakquantity Rmorderinvoiceintakquantity,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			rmorderinvoiceintakquantityservice.addRmorderinvoiceintakquantity(Rmorderinvoiceintakquantity);
			return new UserStatus(1, "Rmorderinvoiceintakquantity added Successfully !");
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
	public @ResponseBody Rmorderinvoiceintakquantity getRmorderinvoiceintakquantity(@PathVariable("id") long id) {
		Rmorderinvoiceintakquantity Rmorderinvoiceintakquantity = null;
		try {
			Rmorderinvoiceintakquantity = rmorderinvoiceintakquantityservice.getRmorderinvoiceintakquantityById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Rmorderinvoiceintakquantity;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateRmorderinvoiceintakquantity(@RequestBody Rmorderinvoiceintakquantity Rmorderinvoiceintakquantity) {
		try {
			rmorderinvoiceintakquantityservice.updateRmorderinvoiceintakquantity(Rmorderinvoiceintakquantity);
			return new UserStatus(1, "Rmorderinvoiceintakquantity update Successfully !");
		} catch (Exception e) {
			// e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}

	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Rmorderinvoiceintakquantity> getRmorderinvoiceintakquantity() {

		List<Rmorderinvoiceintakquantity> RmorderinvoiceintakquantityList = null;
		try {
			RmorderinvoiceintakquantityList = rmorderinvoiceintakquantityservice.getRmorderinvoiceintakquantityList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return RmorderinvoiceintakquantityList;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody UserStatus deleteRmorderinvoiceintakquantity(@PathVariable("id") long id) {

		try {
			Rmorderinvoiceintakquantity Rmorderinvoiceintakquantity = rmorderinvoiceintakquantityservice.getRmorderinvoiceintakquantityById(id);
			Rmorderinvoiceintakquantity.setIsactive(false);
			rmorderinvoiceintakquantityservice.updateRmorderinvoiceintakquantity(Rmorderinvoiceintakquantity);
			return new UserStatus(1, "Rmorderinvoiceintakquantity deleted Successfully !");
		} catch (Exception e) {
			return new UserStatus(0, e.toString());
		}

	}
}

