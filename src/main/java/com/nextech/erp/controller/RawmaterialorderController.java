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

import com.nextech.erp.model.RawmaterialOrderAssociationModel;
import com.nextech.erp.model.Rawmaterialorder;
import com.nextech.erp.model.Rawmaterialorderassociation;
import com.nextech.erp.model.Status;
import com.nextech.erp.model.Vendor;
import com.nextech.erp.service.RawmaterialorderService;
import com.nextech.erp.service.RawmaterialorderassociationService;
import com.nextech.erp.service.StatusService;
import com.nextech.erp.service.VendorService;
import com.nextech.erp.status.UserStatus;

@Controller
@RequestMapping("/rawmaterialorder")
public class RawmaterialorderController {

	@Autowired
	RawmaterialorderService rawmaterialorderService;

	@Autowired
	RawmaterialorderassociationService rawmaterialorderassociationService;

	@Autowired
	StatusService statusService;
	
	@Autowired
	VendorService vendorService;
	
	private static final int STATUS_INVOICE_IN=11;

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addRawmaterialorder(
			@Valid @RequestBody Rawmaterialorder rawmaterialorder,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			rawmaterialorderService.addEntity(rawmaterialorder);
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

	@RequestMapping(value = "/createmultiple", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addMultipleRawmaterialorder(
			@Valid @RequestBody RawmaterialOrderAssociationModel rawmaterialOrderAssociationModel, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError().getDefaultMessage());
			}
			Rawmaterialorder rawmaterialorder= new Rawmaterialorder();
			rawmaterialorder.setCreateDate(new Date());
			rawmaterialorder.setDescription(rawmaterialOrderAssociationModel.getDescription());
			rawmaterialorder.setExpectedDeliveryDate(rawmaterialOrderAssociationModel.getDeliveryDate());
			rawmaterialorder.setQuantity(rawmaterialOrderAssociationModel.getRawmaterialorderassociations().size());
			rawmaterialorder.setStatus(statusService.getEntityById(Status.class,rawmaterialOrderAssociationModel.getStatus()));
			rawmaterialorder.setVendor(vendorService.getEntityById(Vendor.class,rawmaterialOrderAssociationModel.getVendor()));
			rawmaterialorder.setName(rawmaterialOrderAssociationModel.getName());
			rawmaterialorder.setActualPrice(rawmaterialOrderAssociationModel.getActualPrice());
			rawmaterialorder.setOtherCharges(rawmaterialOrderAssociationModel.getOtherCharges());
			rawmaterialorder.setTax(rawmaterialOrderAssociationModel.getTax());
			rawmaterialorder.setTotalprice(rawmaterialOrderAssociationModel.getTotalprice());
			rawmaterialorder.setIsactive(true);
			long id=rawmaterialorderService.addEntity(rawmaterialorder);
			System.out.println("id is"+id);
			List<Rawmaterialorderassociation> rawmaterialorderassociations = rawmaterialOrderAssociationModel.getRawmaterialorderassociations();
			if(rawmaterialorderassociations !=null && !rawmaterialorderassociations.isEmpty()){
				for (Rawmaterialorderassociation rawmaterialorderassociation : rawmaterialorderassociations) {
					rawmaterialorderassociation.setRawmaterialorder(rawmaterialorder);
					rawmaterialorderassociationService.addEntity(rawmaterialorderassociation);
				}
			}
			return new UserStatus(1, "Multiple Rawmaterialorder added Successfully !");
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
	public @ResponseBody Rawmaterialorder getRawmaterialorder(
			@PathVariable("id") long id) {
		Rawmaterialorder rawmaterialorder = null;
		try {
			rawmaterialorder = rawmaterialorderService
					.getEntityById(Rawmaterialorder.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rawmaterialorder;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateRawmaterialorder(
			@RequestBody Rawmaterialorder rawmaterialorder) {
		try {
			rawmaterialorderService.updateEntity(rawmaterialorder);
			return new UserStatus(1, "Rawmaterialorder update Successfully !");
		} catch (Exception e) {
			e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Rawmaterialorder> getRawmaterialorder() {

		List<Rawmaterialorder> rawmaterialorderList = null;
		try {
			rawmaterialorderList = rawmaterialorderService
					.getEntityList(Rawmaterialorder.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rawmaterialorderList;
	}
	@RequestMapping(value = "/list/securityCheck", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Rawmaterialorder> getRMOrderForSecurityCheck() {

		List<Rawmaterialorder> rawmaterialorderList = null;
		try {
			rawmaterialorderList = rawmaterialorderService
					.getRawmaterialorderByStatusId(STATUS_INVOICE_IN);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rawmaterialorderList;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody UserStatus deleteRawmaterialorder(
			@PathVariable("id") long id) {

		try {
			Rawmaterialorder rawmaterialorder = rawmaterialorderService
					.getEntityById(Rawmaterialorder.class,id);
			rawmaterialorder.setIsactive(false);
			rawmaterialorderService.updateEntity(rawmaterialorder);
			return new UserStatus(1, "Rawmaterialorder deleted Successfully !");
		} catch (Exception e) {
			return new UserStatus(0, e.toString());
		}

	}
	
}
