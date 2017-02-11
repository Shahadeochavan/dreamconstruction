package com.nextech.erp.controller;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.nextech.erp.model.Rawmaterialorder;
import com.nextech.erp.model.Rawmaterialorderinvoice;
import com.nextech.erp.model.Rawmaterialorderinvoiceassociation;
import com.nextech.erp.model.Rmorderinvoiceintakquantity;
import com.nextech.erp.service.RawmaterialorderService;
import com.nextech.erp.service.RawmaterialorderinvoiceService;
import com.nextech.erp.service.RawmaterialorderinvoiceassociationService;
import com.nextech.erp.service.RmorderinvoiceintakquantityService;
import com.nextech.erp.status.UserStatus;

@Controller
@RequestMapping("/rawmaterialorderinvoice")
public class RawmaterialorderinvoiceController {

	@Autowired
	RawmaterialorderinvoiceService rawmaterialorderinvoiceservice;

	@Autowired
	RawmaterialorderService rawmaterialorderService;

	@Autowired
	RmorderinvoiceintakquantityService rmorderinvoiceintakquantityService;
	
	@Autowired
	RawmaterialorderinvoiceassociationService rawmaterialorderinvoiceassociationService;

	@RequestMapping(value = "/securitycheck", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addRawmaterialorderinvoice(
			@Valid   @RequestBody Rawmaterialorderinvoice rawmaterialorderinvoice, BindingResult bindingResult,ModelMap map) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError().getDefaultMessage());
			}
		long inid=	rawmaterialorderinvoiceservice.addEntity(rawmaterialorderinvoice);
		
		System.out.println("inid "+inid);
		
			Rawmaterialorder rawmaterialorder = rawmaterialorderService.getEntityById(Rawmaterialorder.class, rawmaterialorderinvoice.getPo_No());
			Rawmaterialorderinvoiceassociation rawmaterialorderinvoiceassociation = new Rawmaterialorderinvoiceassociation();
			rawmaterialorderinvoiceassociation.setRawmaterialorderinvoice(rawmaterialorderinvoice);
			rawmaterialorderinvoiceassociation.setRawmaterialorder(rawmaterialorder);
			rawmaterialorderinvoiceassociation.setIsactive(true);
			rawmaterialorderinvoiceassociationService.addEntity(rawmaterialorderinvoiceassociation);
			List<Rmorderinvoiceintakquantity> rmorderinvoiceintakquantities = rawmaterialorderinvoice.getRmorderinvoiceintakquantities();
			System.out.println("rmorderinvoicequntiets value is="+rawmaterialorderinvoice.getRmorderinvoiceintakquantities());
			if (rmorderinvoiceintakquantities != null && !rmorderinvoiceintakquantities.isEmpty()) {
				for (Rmorderinvoiceintakquantity rmorderinvoiceintakquantity : rmorderinvoiceintakquantities) {
					rmorderinvoiceintakquantity.setRawmaterialorderinvoice(rawmaterialorderinvoice);
					rmorderinvoiceintakquantityService.addEntity(rmorderinvoiceintakquantity);
				}
			}
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

	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Rawmaterialorderinvoice> getRawmaterialorderinvoice() {

		List<Rawmaterialorderinvoice> rawmaterialorderinvoiceList = null;
		try {
			rawmaterialorderinvoiceList = rawmaterialorderinvoiceservice.getEntityList(Rawmaterialorderinvoice.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rawmaterialorderinvoiceList;
	}
}
