package com.nextech.erp.controller;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nextech.erp.constants.ERPConstants;
import com.nextech.erp.model.Rawmaterial;
import com.nextech.erp.model.Rawmaterialvendorassociation;
import com.nextech.erp.service.RawmaterialService;
import com.nextech.erp.status.UserStatus;
@Controller
@RequestMapping("/rawmaterial")
public class RawmaterialController {

	@Autowired
	RawmaterialService rawmaterialService;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addRawmaterial(
			@Valid @RequestBody Rawmaterial rawmaterial, BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			rawmaterial.setIsactive(true);
			rawmaterial.setCreatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
			rawmaterial.setUpdatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
			rawmaterialService.addEntity(rawmaterial);
			return new UserStatus(1, messageSource.getMessage(ERPConstants.RAW_MATERAIL_ADD, null, null));
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
			rawmaterial = rawmaterialService.getEntityById(Rawmaterial.class,id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rawmaterial;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateRawmaterial(@RequestBody Rawmaterial rawmaterial) {
		try {
			rawmaterialService.updateEntity(rawmaterial);
			return new UserStatus(1,messageSource.getMessage(ERPConstants.RAW_MATERAIL_UPDATE, null, null));
		} catch (Exception e) {
			e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Rawmaterial> getRawmaterial() {

		List<Rawmaterial> rawmaterialList = null;
		try {
			rawmaterialList = rawmaterialService.getEntityList(Rawmaterial.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rawmaterialList;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody UserStatus deleteRawmaterial(@PathVariable("id") long id) {

		try {
			Rawmaterial rawmaterial = rawmaterialService.getEntityById(Rawmaterial.class,id);
			rawmaterial.setIsactive(false);
			rawmaterialService.updateEntity(rawmaterial);
			return new UserStatus(1, messageSource.getMessage(ERPConstants.RAW_MATERAIL_DELETE, null, null));
		} catch (Exception e) {
			return new UserStatus(0, e.toString());
		}

	}
	@RequestMapping(value = "/getRMaterial/{VendorId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Rawmaterialvendorassociation> getRawmaterialForVendor(@PathVariable("VendorId") long id) {

		List<Rawmaterialvendorassociation> rawmaterialvendorassociationList = null;
		try {
			rawmaterialvendorassociationList = rawmaterialService.getRawmaterialByVenodrId(id);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rawmaterialvendorassociationList;
	}
	
	@RequestMapping(value = "/getRMForRMOrder/{RMOrderId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Rawmaterial> getRawmaterialForRMOrder(@PathVariable("RMOrderId") long id) {

		List<Rawmaterial> rawmaterialList = null;
		try {
			rawmaterialList = rawmaterialService.getRawMaterialByRMOrderId(id);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rawmaterialList;
	}
}
