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

import com.nextech.erp.model.Rawmaterialorder;
import com.nextech.erp.service.RawmaterialorderService;
import com.nextech.erp.status.UserStatus;

@Controller
@RequestMapping("/rawmaterialorder")
public class RawmaterialorderController {


	@Autowired
	RawmaterialorderService rawmaterialorderService;

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addRawmaterialorder(
			@Valid @RequestBody Rawmaterialorder rawmaterialorder, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			rawmaterialorderService.addRawmaterialorder(rawmaterialorder);
			return new UserStatus(1, "Rawmaterialorder added Successfully !");
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
	public @ResponseBody Rawmaterialorder getRawmaterialorder(@PathVariable("id") long id) {
		Rawmaterialorder rawmaterialorder = null;
		try {
			rawmaterialorder = rawmaterialorderService.getRawmaterialorderById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rawmaterialorder;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateRawmaterialorder(@RequestBody Rawmaterialorder rawmaterialorder) {
		try {
			rawmaterialorderService.updateRawmaterialorder(rawmaterialorder);
			return new UserStatus(1, "Rawmaterialorder update Successfully !");
		} catch (Exception e) {
			e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}

	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Rawmaterialorder> getRawmaterialorder() {

		List<Rawmaterialorder> rawmaterialorderList = null;
		try {
			rawmaterialorderList = rawmaterialorderService.getRawmaterialorderList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rawmaterialorderList;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody UserStatus deleteRawmaterialorder(@PathVariable("id") long id) {

		try {
			Rawmaterialorder rawmaterialorder = rawmaterialorderService.getRawmaterialorderById(id);
			rawmaterialorder.setIsactive(false);
			rawmaterialorderService.updateRawmaterialorder(rawmaterialorder);
			return new UserStatus(1, "Rawmaterialorder deleted Successfully !");
		} catch (Exception e) {
			return new UserStatus(0, e.toString());
		}

	}
}
