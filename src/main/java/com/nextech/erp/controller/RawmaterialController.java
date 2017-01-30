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

import com.nextech.erp.model.Rawmaterial;
import com.nextech.erp.service.RawmaterialService;
import com.nextech.erp.status.UserStatus;
@Controller
@RequestMapping("/rawmaterial")
public class RawmaterialController {

	@Autowired
	RawmaterialService rawmaterialService;

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addRawmaterial(
			@Valid @RequestBody Rawmaterial rawmaterial, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			rawmaterialService.addRawmaterial(rawmaterial);
			return new UserStatus(1, "Rawmaterial added Successfully !");
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
	public @ResponseBody Rawmaterial getRawmaterial(@PathVariable("id") long id) {
		Rawmaterial rawmaterial = null;
		try {
			rawmaterial = rawmaterialService.getRawmaterialById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rawmaterial;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateRawmaterial(@RequestBody Rawmaterial rawmaterial) {
		try {
			rawmaterialService.updateRawmaterial(rawmaterial);
			return new UserStatus(1, "Rawmaterial update Successfully !");
		} catch (Exception e) {
			e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}

	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Rawmaterial> getRawmaterial() {

		List<Rawmaterial> rawmaterialList = null;
		try {
			rawmaterialList = rawmaterialService.getRawmaterialList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rawmaterialList;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody UserStatus deleteRawmaterial(@PathVariable("id") long id) {

		try {
			Rawmaterial rawmaterial = rawmaterialService.getRawmaterialById(id);
			rawmaterial.setIsactive(false);
			rawmaterialService.updateRawmaterial(rawmaterial);
			return new UserStatus(1, "Rawmaterial deleted Successfully !");
		} catch (Exception e) {
			return new UserStatus(0, e.toString());
		}

	}
}
