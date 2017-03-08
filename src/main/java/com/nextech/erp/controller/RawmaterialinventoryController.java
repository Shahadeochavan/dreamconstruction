package com.nextech.erp.controller;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nextech.erp.constants.ERPConstants;
import com.nextech.erp.model.Rawmaterialinventory;
import com.nextech.erp.service.RawmaterialinventoryService;
import com.nextech.erp.status.UserStatus;

@RestController
@RequestMapping("/rawmaterialinventory")
public class RawmaterialinventoryController {

	@Autowired
	RawmaterialinventoryService rawmaterialinventoryService;

	@Autowired
	private MessageSource messageSource;
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addRawmaterialinventory(
			@Valid @RequestBody Rawmaterialinventory rawmaterialinventory, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			if(rawmaterialinventoryService.getByRMId(rawmaterialinventory.getRawmaterial().getId())==null){
				rawmaterialinventoryService.addEntity(rawmaterialinventory);
			}
			else
				return new UserStatus(1, messageSource.getMessage(
						ERPConstants.RAW_MATERIAL_INVENTORY, null, null));
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
		Rawmaterialinventory rawmaterialinventory = null;
		try {
			rawmaterialinventory = rawmaterialinventoryService.getEntityById(Rawmaterialinventory.class,id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rawmaterialinventory;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateRawmaterialinventory(@RequestBody Rawmaterialinventory rawmaterialinventory) {
		try {
			rawmaterialinventoryService.updateEntity(rawmaterialinventory);
			return new UserStatus(1, "Rawmaterialinventory update Successfully !");
		} catch (Exception e) {
			e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Rawmaterialinventory> getRawmaterialinventory() {

		List<Rawmaterialinventory> rawmaterialinventoryList = null;
		try {
			rawmaterialinventoryList = rawmaterialinventoryService.getEntityList(Rawmaterialinventory.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rawmaterialinventoryList;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody UserStatus deleteRawmaterialinventory(@PathVariable("id") long id) {

		try {
			Rawmaterialinventory rawmaterialinventory = rawmaterialinventoryService.getEntityById(Rawmaterialinventory.class,id);
			rawmaterialinventory.setIsactive(false);
			rawmaterialinventoryService.updateEntity(rawmaterialinventory);
			return new UserStatus(1, "Rawmaterialinventory deleted Successfully !");
		} catch (Exception e) {
			return new UserStatus(0, e.toString());
		}

	}
}
