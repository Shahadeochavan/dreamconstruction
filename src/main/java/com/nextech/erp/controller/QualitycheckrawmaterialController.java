package com.nextech.erp.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nextech.erp.model.Qualitycheckrawmaterial;
import com.nextech.erp.model.Rawmaterial;
import com.nextech.erp.model.Rawmaterialinventory;
import com.nextech.erp.model.Rawmaterialinventoryhistory;
import com.nextech.erp.model.Rawmaterialorder;
import com.nextech.erp.model.Rawmaterialorderhistory;
import com.nextech.erp.model.Rawmaterialorderinvoice;
import com.nextech.erp.model.Rmorderinvoiceintakquantity;
import com.nextech.erp.model.Status;
import com.nextech.erp.service.QualitycheckrawmaterialService;
import com.nextech.erp.service.RawmaterialService;
import com.nextech.erp.service.RawmaterialinventoryService;
import com.nextech.erp.service.RawmaterialinventoryhistoryService;
import com.nextech.erp.service.RawmaterialorderService;
import com.nextech.erp.service.RawmaterialorderhistoryService;
import com.nextech.erp.service.RawmaterialorderinvoiceService;
import com.nextech.erp.service.RawmaterialorderinvoiceassociationService;
import com.nextech.erp.service.RmorderinvoiceintakquantityService;
import com.nextech.erp.service.StatusService;
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
	
	@Autowired
	RawmaterialorderhistoryService rawmaterialorderhistoryService;
	
	@Autowired
	RawmaterialinventoryhistoryService rawmaterialinventoryhistoryService;
	
	@Autowired
	RawmaterialorderService rawmaterialorderService; 
	
	@Autowired
	RawmaterialinventoryService rawmaterialinventoryService; 
	
	@Autowired
	StatusService statusService;
	
	@Autowired
	RawmaterialorderinvoiceassociationService rawmaterialorderinvoiceassociationService;

	@RequestMapping(value = "/qualitycheck", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	@Transactional
	public @ResponseBody UserStatus addRawmaterialorderinvoice(
			@Valid @RequestBody Rawmaterialorderinvoice rawmaterialorderinvoice,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			String message = "";
			
			Rawmaterialorderinvoice rawmaterialorderinvoiceNew = rawmaterialorderinvoiceService.getEntityById(Rawmaterialorderinvoice.class,rawmaterialorderinvoice.getId());
			List<Qualitycheckrawmaterial> qualitycheckrawmaterials = rawmaterialorderinvoice.getQualitycheckrawmaterials();
			if (qualitycheckrawmaterials != null&& !qualitycheckrawmaterials.isEmpty()) {
				for (Qualitycheckrawmaterial qualitycheckrawmaterial : qualitycheckrawmaterials) {
					qualitycheckrawmaterial.setRawmaterialorderinvoice(rawmaterialorderinvoiceNew);
					Rawmaterial rawmaterial = rawmaterialService.getEntityById(Rawmaterial.class, qualitycheckrawmaterial.getRawmaterial().getId());
					qualitycheckrawmaterial.setRawmaterial(rawmaterial);
					qualitycheckrawmaterial.setGoodQuantity(qualitycheckrawmaterial.getGoodQuantity());
					qualitycheckrawmaterial.setIntakeQuantity(qualitycheckrawmaterial.getIntakeQuantity());
					if(qualitycheckrawmaterialService.getQualitycheckrawmaterialByInvoiceIdAndRMId(qualitycheckrawmaterial.getRawmaterialorderinvoice().getId(), 
							qualitycheckrawmaterial.getRawmaterial().getId())==null){
					long inid =	qualitycheckrawmaterialService.addEntity(qualitycheckrawmaterial);
					System.out.println("inid " + inid);	
					
					
					// TODO  call to order history
					Rawmaterialorder rawmaterialorder = rawmaterialorderService.getEntityById(Rawmaterialorder.class, rawmaterialorderinvoiceNew.getPo_No());
					Rawmaterialorderhistory rawmaterialorderhistory = new Rawmaterialorderhistory();
					rawmaterialorderhistory.setComment(rawmaterialorderinvoice.getDescription());
					rawmaterialorderhistory.setRawmaterialorder(rawmaterialorder);
					rawmaterialorderhistory.setRawmaterialorderinvoice(rawmaterialorderinvoice);
					rawmaterialorderhistory.setCreatedDate(new Timestamp(new Date().getTime()));
					rawmaterialorderhistory.setQualitycheckrawmaterial(qualitycheckrawmaterial);
					rawmaterialorderhistory.setStatus1(statusService.getEntityById(Status.class,rawmaterialorder.getStatus().getId()));
					//TODO To status is not set correctly
					rawmaterialorderhistory.setStatus2(statusService.getEntityById(Status.class, rawmaterialorderinvoiceNew.getStatus().getId()));
					rawmaterialorderhistory.setCreatedBy(3);
					rawmaterialorderhistoryService.addEntity(rawmaterialorderhistory);
					
					// TODO  update inventory
					Rawmaterialinventory rawmaterialinventory =  rawmaterialinventoryService.getByRMId(qualitycheckrawmaterial.getRawmaterial().getId());
					if(rawmaterialinventory == null){
						rawmaterialinventory = new Rawmaterialinventory();
						rawmaterialinventory.setRawmaterial(rawmaterial);
					}
					rawmaterialinventory.setQuantityAvailable(rawmaterialinventory.getQuantityAvailable()+qualitycheckrawmaterial.getGoodQuantity());
					rawmaterialinventory.setUpdatedDate(new Timestamp(new Date().getTime()));
					rawmaterialinventoryService.updateEntity(rawmaterialinventory);
					
					// TODO  call to inventory history
					Rawmaterialinventoryhistory rawmaterialinventoryhistory = new Rawmaterialinventoryhistory();
					rawmaterialinventoryhistory.setQualitycheckrawmaterial(qualitycheckrawmaterial);
					rawmaterialinventoryhistory.setRawmaterialinventory(rawmaterialinventory);
					rawmaterialinventoryhistory.setCreatedDate(new Timestamp(new Date().getTime()));
					rawmaterialinventoryhistory.setStatus(statusService.getEntityById(Status.class,rawmaterialorderinvoiceNew.getStatus().getId()));
					rawmaterialinventoryhistoryService.addEntity(rawmaterialinventoryhistory);
					message = "Qualitycheckrawmaterial added Successfully !";
				}else {
					message += " Invoice id = " + rawmaterialorderinvoice.getId() + " raw material id = " + qualitycheckrawmaterial.getRawmaterial().getId() + " already exists";
					//return new UserStatus(1, "Rawmaterialorderinvoice id and rawmaterialid already exists !");
				}
				
			}
			}
			
			
			
			/*for (Qualitycheckrawmaterial qualitycheckrawmaterial : rawmaterialorderinvoice.getQualitycheckrawmaterials()) {
				// TODO  update inventory
				Rawmaterialinventory rawmaterialinventory =  rawmaterialinventoryService.getByRMId(qualitycheckrawmaterial.getRawmaterial().getId());
				rawmaterialinventory.setQuantityAvailable(rawmaterialinventory.getQuantityAvailable()+qualitycheckrawmaterial.getGoodQuantity());
				rawmaterialinventory.setUpdatedDate(new Timestamp(new Date().getTime()));
				rawmaterialinventoryService.updateEntity(rawmaterialinventory);
				
				// TODO  call to inventory history
				Rawmaterialinventoryhistory rawmaterialinventoryhistory = new Rawmaterialinventoryhistory();
				rawmaterialinventoryhistory.setQualitycheckrawmaterial(qualitycheckrawmaterial);
				rawmaterialinventoryhistory.setRawmaterialinventory(rawmaterialinventory);
				rawmaterialinventoryhistory.setCreatedDate(new Timestamp(new Date().getTime()));
				rawmaterialinventoryhistoryService.addEntity(rawmaterialinventoryhistory);
			}*/
			
			
			
			// TODO  call to trigger notification (will do it later )
			return new UserStatus(1,
					message);
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
	public @ResponseBody List<Rmorderinvoiceintakquantity> getRmorderinvoiceintakquantityByRMOInvoiceId(
			@PathVariable("id") long id) {
		List<Rmorderinvoiceintakquantity> rmorderinvoiceintakquantities = null;
		try {
			rmorderinvoiceintakquantities = rmorderinvoiceintakquantityService.getRmorderinvoiceintakquantityByRMOInvoiceId(id);
//			rawmaterialList = new ArrayList<Rawmaterial>();
			System.out.println("list size "
					+ rmorderinvoiceintakquantities.size());
			for (Rmorderinvoiceintakquantity rmorderinvoiceintakquantity : rmorderinvoiceintakquantities) {
				Rawmaterial rawmaterial = rawmaterialService.getEntityById(
						Rawmaterial.class, rmorderinvoiceintakquantity
								.getRawmaterial().getId());
//				qualitycheckrawmaterials.add(rawmaterial);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rmorderinvoiceintakquantities;
	}
}
