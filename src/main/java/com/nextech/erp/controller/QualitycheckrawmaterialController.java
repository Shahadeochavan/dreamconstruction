package com.nextech.erp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nextech.erp.model.Qualitycheckrawmaterial;
import com.nextech.erp.model.Rawmaterial;
import com.nextech.erp.model.Rawmaterialorderinvoice;
import com.nextech.erp.model.Rmorderinvoiceintakquantity;
import com.nextech.erp.service.QualitycheckrawmaterialService;
import com.nextech.erp.service.RawmaterialService;
import com.nextech.erp.service.RawmaterialorderinvoiceService;
import com.nextech.erp.service.RmorderinvoiceintakquantityService;
import com.nextech.erp.status.UserStatus;

@Controller
@RequestMapping("/qualitycheckrawmaterial")
public class QualitycheckrawmaterialController {
	@Autowired
	QualitycheckrawmaterialService qualitycheckrawmaterialService;

	@Autowired
	RmorderinvoiceintakquantityService rmorderinvoiceintakquantityService;

	@Autowired
	RawmaterialService rawmaterialService;

	@Autowired
	RawmaterialorderinvoiceService rawmaterialorderinvoiceService;

	@RequestMapping(value = "/qualitycheck", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addRawmaterialorderinvoice(
			@Valid @RequestBody Qualitycheckrawmaterial qualitycheckrawmaterial,
			BindingResult bindingResult, ModelMap map) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			Rawmaterialorderinvoice rawmaterialorderinvoice = rawmaterialorderinvoiceService.getEntityById(Rawmaterialorderinvoice.class,qualitycheckrawmaterial.getRawmaterialorderinvoice());
			List<Rmorderinvoiceintakquantity> rmorderinvoiceintakquantities = rawmaterialorderinvoice
					.getRmorderinvoiceintakquantities();
			if (rmorderinvoiceintakquantities != null&& !rmorderinvoiceintakquantities.isEmpty()) {
				for (Rmorderinvoiceintakquantity rmorderinvoiceintakquantity : rmorderinvoiceintakquantities) {
					rmorderinvoiceintakquantity
							.setRawmaterialorderinvoice(rawmaterialorderinvoice);
					qualitycheckrawmaterialService
							.addEntity(qualitycheckrawmaterial);
				}
			}
			return new UserStatus(1,
					"Qualitycheckrawmaterial added Successfully !");
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

	@RequestMapping(value = "listrm/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Rawmaterial> getRmorderinvoiceintakquantityByRMOInvoiceId(
			@PathVariable("id") long id) {
		List<Rawmaterial> rawmaterialList = null;
		try {
			List<Rmorderinvoiceintakquantity> rmorderinvoiceintakquantities = rmorderinvoiceintakquantityService
					.getRmorderinvoiceintakquantityByRMOInvoiceId(id);
			rawmaterialList = new ArrayList<Rawmaterial>();
			System.out.println("list size "
					+ rmorderinvoiceintakquantities.size());
			for (Rmorderinvoiceintakquantity rmorderinvoiceintakquantity : rmorderinvoiceintakquantities) {
				Rawmaterial rawmaterial = rawmaterialService.getEntityById(
						Rawmaterial.class, rmorderinvoiceintakquantity
								.getRawmaterial().getId());
				rawmaterialList.add(rawmaterial);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rawmaterialList;
	}
}
