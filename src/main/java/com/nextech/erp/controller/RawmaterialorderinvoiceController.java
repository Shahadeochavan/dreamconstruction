package com.nextech.erp.controller;

import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nextech.erp.model.Rawmaterialorder;
import com.nextech.erp.model.Rawmaterialorderassociation;
import com.nextech.erp.model.Rawmaterialorderinvoice;
import com.nextech.erp.model.Rawmaterialorderinvoiceassociation;
import com.nextech.erp.model.Status;
import com.nextech.erp.model.Vendor;
import com.nextech.erp.service.RawmaterialorderService;
import com.nextech.erp.service.RawmaterialorderinvoiceService;
import com.nextech.erp.service.RawmaterialorderinvoiceassociationService;
import com.nextech.erp.status.UserStatus;

@Controller
@RequestMapping("/rawmaterialorderinvoice")
public class RawmaterialorderinvoiceController {
	private static final long id = 0;

	@Autowired
	RawmaterialorderinvoiceService rawmaterialorderinvoiceservice;
	
	@Autowired
	RawmaterialorderService rawmaterialorderService;
	
	@Autowired
	RawmaterialorderinvoiceassociationService rawmaterialorderinvoiceassociationService;

	@RequestMapping(value = "/securitycheck", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addRawmaterialorderinvoice(
			@Valid @RequestBody Rawmaterialorderinvoice rawmaterialorderinvoice, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError().getDefaultMessage());
			}
			 rawmaterialorderinvoiceservice.addEntity(rawmaterialorderinvoice);
			Rawmaterialorder rawmaterialorder=new Rawmaterialorder();
		List<Rawmaterialorderinvoiceassociation> rawmaterialorderinvoiceassociations = (List<Rawmaterialorderinvoiceassociation>) rawmaterialorderinvoiceservice.getEntityById(Rawmaterialorderinvoice.class, id);
		rawmaterialorderinvoiceassociations =(List<Rawmaterialorderinvoiceassociation>) rawmaterialorderService.getEntityById(Rawmaterialorder.class, id);
			if(rawmaterialorderinvoiceassociations !=null && !rawmaterialorderinvoiceassociations.isEmpty()){
				for (Rawmaterialorderinvoiceassociation rawmaterialorderinvoiceassociation : rawmaterialorderinvoiceassociations) {
					rawmaterialorderinvoiceassociation.setRawmaterialorderinvoice(rawmaterialorderinvoice);
					rawmaterialorderinvoiceassociation.setRawmaterialorder(rawmaterialorder);
					rawmaterialorderinvoiceassociationService.addEntity(rawmaterialorderinvoiceassociation);
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
			rawmaterialorderinvoiceList = rawmaterialorderinvoiceservice
					.getEntityList(Rawmaterialorderinvoice.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rawmaterialorderinvoiceList;
	}
}
