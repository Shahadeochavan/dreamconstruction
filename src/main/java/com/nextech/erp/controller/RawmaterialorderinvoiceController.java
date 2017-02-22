package com.nextech.erp.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nextech.erp.model.Rawmaterialorder;
import com.nextech.erp.model.Rawmaterialorderhistory;
import com.nextech.erp.model.Rawmaterialorderinvoice;
import com.nextech.erp.model.Rawmaterialorderinvoiceassociation;
import com.nextech.erp.model.Rmorderinvoiceintakquantity;
import com.nextech.erp.model.Status;
import com.nextech.erp.service.RawmaterialorderService;
import com.nextech.erp.service.RawmaterialorderhistoryService;
import com.nextech.erp.service.RawmaterialorderinvoiceService;
import com.nextech.erp.service.RawmaterialorderinvoiceassociationService;
import com.nextech.erp.service.RmorderinvoiceintakquantityService;
import com.nextech.erp.service.StatusService;
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
	@Autowired
	StatusService statusService;
	@Autowired
	RawmaterialorderhistoryService rawmaterialorderhistoryService;
	
	private static final int STATUS_SECURITY_CHECK_INVOICE_IN = 8;

	@RequestMapping(value = "/securitycheck", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addRawmaterialorderinvoice(
			@Valid @RequestBody Rawmaterialorderinvoice rawmaterialorderinvoice,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			
			//Check for duplicate rows
			if (rawmaterialorderinvoiceservice.getRMOrderInvoiceByInVoiceNoVendorNameAndPoNo(rawmaterialorderinvoice.getInvoice_No(),
					rawmaterialorderinvoice.getVendorname(),rawmaterialorderinvoice.getPo_No())== null) {
				rawmaterialorderinvoice.setStatus(statusService.getEntityById(Status.class, STATUS_SECURITY_CHECK_INVOICE_IN));
				long inid = rawmaterialorderinvoiceservice
						.addEntity(rawmaterialorderinvoice);
				System.out.println("inid " + inid);
				
				Rawmaterialorder rawmaterialorder = rawmaterialorderService
						.getEntityById(Rawmaterialorder.class,
								rawmaterialorderinvoice.getPo_No());
				
				
				Rawmaterialorderinvoiceassociation rawmaterialorderinvoiceassociation = new Rawmaterialorderinvoiceassociation();
				rawmaterialorderinvoiceassociation
						.setRawmaterialorderinvoice(rawmaterialorderinvoice);
				rawmaterialorderinvoiceassociation
						.setRawmaterialorder(rawmaterialorder);
				rawmaterialorderinvoiceassociation.setIsactive(true);
				rawmaterialorderinvoiceassociationService
						.addEntity(rawmaterialorderinvoiceassociation);
				List<Rmorderinvoiceintakquantity> rmorderinvoiceintakquantities = rawmaterialorderinvoice
						.getRmorderinvoiceintakquantities();
				System.out.println("rmorderinvoicequntiets value is="
						+ rawmaterialorderinvoice
								.getRmorderinvoiceintakquantities());
				if (rmorderinvoiceintakquantities != null
						&& !rmorderinvoiceintakquantities.isEmpty()) {
					for (Rmorderinvoiceintakquantity rmorderinvoiceintakquantity : rmorderinvoiceintakquantities) {
						rmorderinvoiceintakquantity
								.setRawmaterialorderinvoice(rawmaterialorderinvoice);
						rmorderinvoiceintakquantityService
								.addEntity(rmorderinvoiceintakquantity);
					}
				}
				//TODO call to order history
				Rawmaterialorderhistory rawmaterialorderhistory = new Rawmaterialorderhistory();
				rawmaterialorderhistory.setComment(rawmaterialorderinvoice.getDescription());
				rawmaterialorderhistory.setRawmaterialorder(rawmaterialorder);
				rawmaterialorderhistory.setRawmaterialorderinvoice(rawmaterialorderinvoice);
				rawmaterialorderhistory.setCreatedDate(new Timestamp(new Date().getTime()));
				//rawmaterialorderhistory.setQualitycheckrawmaterial(qualitycheckrawmaterial);
				rawmaterialorderhistory.setStatus1(statusService.getEntityById(Status.class,rawmaterialorder.getStatus().getId()));
				//TODO To status is not set correctly
				rawmaterialorderhistory.setStatus2(statusService.getEntityById(Status.class, STATUS_SECURITY_CHECK_INVOICE_IN));
				rawmaterialorderhistory.setCreatedBy(3);
			//	rawmaterialorderhistory.setRawmaterialorder(rawmaterialorderinvoiceassociationService.getEntityById(Rawmaterialorderinvoiceassociation.class, id)
				rawmaterialorderhistoryService.addEntity(rawmaterialorderhistory);
				
				//change status to Quality Check
				rawmaterialorder.setStatus(statusService.getEntityById(Status.class, STATUS_SECURITY_CHECK_INVOICE_IN));
				rawmaterialorderService.updateEntity(rawmaterialorder);
				return new UserStatus(1,
						"Rawmaterialorderinvoice added Successfully !");

			} else {
				return new UserStatus(1, "Rawmaterialorderinvoice number already exists !");
			}
			


			
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
	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateRawmaterialorderinvoice(
			@RequestBody Rawmaterialorderinvoice rawmaterialorderinvoice) {
		try {
			rawmaterialorderinvoiceservice
					.updateEntity(rawmaterialorderinvoice);
			return new UserStatus(1,
					"Rawmaterialorderinvoice update Successfully !");
		} catch (Exception e) {
			e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}

	@RequestMapping(value = "security-in-invoices", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Rawmaterialorderinvoice> getRawmaterialorderinvoiceByStatusId() {
		List<Rawmaterialorderinvoice> rawmaterialorderinvoiceList = null;
		try {
			List<Rawmaterialorderinvoice> rawmaterialorderinvoices = rawmaterialorderinvoiceservice
					.getRawmaterialorderinvoiceByStatusId(8l);
			rawmaterialorderinvoiceList = new ArrayList<Rawmaterialorderinvoice>();
			System.out.println("list size " + rawmaterialorderinvoices.size());
			if (rawmaterialorderinvoices != null
					&& !rawmaterialorderinvoices.isEmpty()) {
				for (Rawmaterialorderinvoice rawmaterialorderinvoice : rawmaterialorderinvoices) {
					rawmaterialorderinvoiceList.add(rawmaterialorderinvoice);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rawmaterialorderinvoiceList;
	}
}
