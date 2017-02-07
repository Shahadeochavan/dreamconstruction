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
import com.nextech.erp.model.Rawmaterialorderinvoice;
import com.nextech.erp.service.RawmaterialorderinvoiceService;
import com.nextech.erp.status.UserStatus;

@Controller
@RequestMapping("/rawmaterialorderinvoice")
public class RawmaterialorderinvoiceController {
	@Autowired
	RawmaterialorderinvoiceService rawmaterialorderinvoiceservice;

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addRawmaterialorderinvoice(
			@Valid @RequestBody Rawmaterialorderinvoice rawmaterialorderinvoice, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError().getDefaultMessage());
			}
			Long RMOrderinvoiceId = rawmaterialorderinvoiceservice.addRawmaterialorderinvoice(rawmaterialorderinvoice);

			return new UserStatus(1, "Rawmaterialorderinvoice added Successfully !");
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
	public @ResponseBody Rawmaterialorderinvoice getRawmaterialorderinvoice(@PathVariable("id") long id) {
		Rawmaterialorderinvoice rawmaterialorderinvoice = null;
		try {
			rawmaterialorderinvoice = rawmaterialorderinvoiceservice.getRawmaterialorderinvoiceById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rawmaterialorderinvoice;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateRawmaterialorderinvoice(
			@RequestBody Rawmaterialorderinvoice rawmaterialorderinvoice) {
		try {
			rawmaterialorderinvoiceservice.updateRawmaterialorderinvoice(rawmaterialorderinvoice);
			return new UserStatus(1, "Rawmaterialorderinvoice update Successfully !");
		} catch (Exception e) {
			// e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}

	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Rawmaterialorderinvoice> getRawmaterialorderinvoice() {

		List<Rawmaterialorderinvoice> rawmaterialorderinvoiceList = null;
		try {
			rawmaterialorderinvoiceList = rawmaterialorderinvoiceservice.getRawmaterialorderinvoiceList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rawmaterialorderinvoiceList;
	}
}
