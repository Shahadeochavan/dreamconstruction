package com.nextech.erp.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nextech.erp.constants.ERPConstants;
import com.nextech.erp.model.Qualitycheckrawmaterial;
import com.nextech.erp.model.Rawmaterial;
import com.nextech.erp.model.Rawmaterialinventory;
import com.nextech.erp.model.Rawmaterialinventoryhistory;
import com.nextech.erp.model.Rawmaterialorder;
import com.nextech.erp.model.Rawmaterialorderassociation;
import com.nextech.erp.model.Rawmaterialorderhistory;
import com.nextech.erp.model.Rawmaterialorderinvoice;
import com.nextech.erp.model.Rmorderinvoiceintakquantity;
import com.nextech.erp.model.Status;
import com.nextech.erp.service.QualitycheckrawmaterialService;
import com.nextech.erp.service.RawmaterialService;
import com.nextech.erp.service.RawmaterialinventoryService;
import com.nextech.erp.service.RawmaterialinventoryhistoryService;
import com.nextech.erp.service.RawmaterialorderService;
import com.nextech.erp.service.RawmaterialorderassociationService;
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
	
	@Autowired
	RawmaterialorderassociationService rawmaterialorderassociationService;
	
	@Autowired
	private MessageSource messageSource;
	
	private HashMap<Long,Integer> rmIdQuantityMap;
	
	//private static final int STATUS_RAW_MATERIAL_INVENTORY_ADD=12;
	private static final int STATUS_RAW_MATERIAL_ORDER_INCOMPLETE=2;
	private static final int STATUS_RAW_MATERIAL_ORDER_COMPLETE=3;

	@RequestMapping(value = "/qualitycheck", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	@Transactional
	public @ResponseBody UserStatus addRawmaterialorderinvoice(
			@Valid @RequestBody Rawmaterialorderinvoice rawmaterialorderinvoice,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError().getDefaultMessage());
			}
			String message = "";
			Rawmaterialorderinvoice rawmaterialorderinvoiceNew = rawmaterialorderinvoiceService.getEntityById(Rawmaterialorderinvoice.class,rawmaterialorderinvoice.getId());
			Rawmaterialorder rawmaterialorder = rawmaterialorderService.getEntityById(Rawmaterialorder.class, rawmaterialorderinvoiceNew.getPo_No());
			List<Qualitycheckrawmaterial> qualitycheckrawmaterials = rawmaterialorderinvoice.getQualitycheckrawmaterials();
			if (qualitycheckrawmaterials != null&& !qualitycheckrawmaterials.isEmpty()) {
				for (Qualitycheckrawmaterial qualitycheckrawmaterial : qualitycheckrawmaterials) {
					Rawmaterial rawmaterial = rawmaterialService.getEntityById(Rawmaterial.class, qualitycheckrawmaterial.getRawmaterial().getId());
					
					//TODO save Quality check
					long qualityCheckId = saveQualityCheck(qualitycheckrawmaterial, rawmaterialorderinvoiceNew,rawmaterial);
					//if qualityCheckId value is 0 that means this is duplicate entry
					if(qualityCheckId != 0 ){
						// TODO  update inventory
						Rawmaterialinventory rawmaterialinventory = updateInventory(qualitycheckrawmaterial, rawmaterial);
	
						// TODO  call to inventory history
						addRMInventoryHistory(qualitycheckrawmaterial, rawmaterialinventory);
						
						
						
						//TODO update raw material invoice
						updateRawMaterialInvoice(rawmaterialorderinvoiceNew);
						
						//TODO update raw material order
						updateRMOrderRemainingQuantity(qualitycheckrawmaterial, rawmaterialorder);
						
						updateRMIdQuantityMap(qualitycheckrawmaterial.getRawmaterial().getId(), qualitycheckrawmaterial.getIntakeQuantity() - qualitycheckrawmaterial.getGoodQuantity());
						message = messageSource.getMessage(ERPConstants.RM_QUALITY_CHECK, null, null);
					}else{
						message += " Invoice id = " + rawmaterialorderinvoiceNew.getId() + " raw material id = " + qualitycheckrawmaterial.getRawmaterial().getId() + " already exists";
					}
			}
			}else{
				return new UserStatus(0, messageSource.getMessage(ERPConstants.INFO_QUALITY_CHECK, null, null));
			}
			// TODO  call to order history
			addOrderHistory(rawmaterialorderinvoiceNew, rawmaterialorder);
			//TODO update raw material order
			updateRawMaterialOrder(rawmaterialorder);
			

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

	private void updateRawMaterialOrder(Rawmaterialorder rawmaterialorder) throws Exception{
		
		rawmaterialorder.setStatus(statusService.getEntityById(Status.class, getOrderStatus(rawmaterialorder)));
		rawmaterialorderService.updateEntity(rawmaterialorder);
	}
	
	private int getOrderStatus(Rawmaterialorder rawmaterialorder) throws Exception{
		boolean isOrderComplete = false;
		List<Rawmaterialorderassociation> rawmaterialorderassociationList  =rawmaterialorderassociationService.getRMOrderRMAssociationByRMOrderId(rawmaterialorder.getId());
		for (Iterator<Rawmaterialorderassociation> iterator = rawmaterialorderassociationList.iterator(); iterator
				.hasNext();) {
			Rawmaterialorderassociation rawmaterialorderassociation = (Rawmaterialorderassociation) iterator
					.next();
			if(rawmaterialorderassociation.getRemainingQuantity() ==0 ){
				isOrderComplete = true;
			}else{
				isOrderComplete = false;
				break;
			}
			
		}
		return isOrderComplete ? STATUS_RAW_MATERIAL_ORDER_COMPLETE : STATUS_RAW_MATERIAL_ORDER_INCOMPLETE;
	}
	
	private void updateRawMaterialInvoice(Rawmaterialorderinvoice rawmaterialorderinvoice) throws Exception{
		rawmaterialorderinvoice.setStatus(statusService.getEntityById(Status.class, Long.parseLong(messageSource.getMessage(ERPConstants.STATUS_RAW_MATERIAL_INVENTORY_ADD, null, null))));
		rawmaterialorderinvoiceService.updateEntity(rawmaterialorderinvoice);
	}

	private long saveQualityCheck(Qualitycheckrawmaterial qualitycheckrawmaterial,Rawmaterialorderinvoice rawmaterialorderinvoiceNew,Rawmaterial rawmaterial) throws Exception{
		String message = "";
		qualitycheckrawmaterial.setRawmaterialorderinvoice(rawmaterialorderinvoiceNew);
		qualitycheckrawmaterial.setRawmaterial(rawmaterial);
		qualitycheckrawmaterial.setGoodQuantity(qualitycheckrawmaterial.getGoodQuantity());
		qualitycheckrawmaterial.setIntakeQuantity(qualitycheckrawmaterial.getIntakeQuantity());
		if(qualitycheckrawmaterialService.getQualitycheckrawmaterialByInvoiceIdAndRMId(qualitycheckrawmaterial.getRawmaterialorderinvoice().getId(), 
				qualitycheckrawmaterial.getRawmaterial().getId())==null){
			qualitycheckrawmaterialService.addEntity(qualitycheckrawmaterial);
		}else {
			message += " Invoice id = " + rawmaterialorderinvoiceNew.getId() + " raw material id = " + qualitycheckrawmaterial.getRawmaterial().getId() + " already exists";
		}
		return qualitycheckrawmaterial.getId();
	}
		
	private Rawmaterialinventory updateInventory(Qualitycheckrawmaterial qualitycheckrawmaterial,Rawmaterial rawmaterial) throws Exception{
		Rawmaterialinventory rawmaterialinventory =  rawmaterialinventoryService.getByRMId(qualitycheckrawmaterial.getRawmaterial().getId());
		if(rawmaterialinventory == null){
			rawmaterialinventory = new Rawmaterialinventory();
			rawmaterialinventory.setRawmaterial(rawmaterial);
		}
		rawmaterialinventory.setQuantityAvailable(rawmaterialinventory.getQuantityAvailable()+qualitycheckrawmaterial.getGoodQuantity());
		rawmaterialinventory.setUpdatedDate(new Timestamp(new Date().getTime()));
		return rawmaterialinventoryService.updateEntity(rawmaterialinventory);
	}
	private void  updateRMOrderRemainingQuantity(Qualitycheckrawmaterial qualitycheckrawmaterial ,Rawmaterialorder rawmaterialorder) throws Exception{
		Rawmaterialorderassociation rawmaterialorderassociation  =rawmaterialorderassociationService.getRMOrderRMAssociationByRMOrderIdandRMId(rawmaterialorder.getId(),qualitycheckrawmaterial.getRawmaterial().getId());
		rawmaterialorderassociation.setRemainingQuantity(rawmaterialorderassociation.getRemainingQuantity()-qualitycheckrawmaterial.getGoodQuantity());
		rawmaterialorderassociationService.updateEntity(rawmaterialorderassociation);
	}
	
	private void addRMInventoryHistory(Qualitycheckrawmaterial qualitycheckrawmaterial,Rawmaterialinventory rawmaterialinventory) throws Exception{
		Rawmaterialinventoryhistory rawmaterialinventoryhistory = new Rawmaterialinventoryhistory();
		rawmaterialinventoryhistory.setQualitycheckrawmaterial(qualitycheckrawmaterial);
		rawmaterialinventoryhistory.setRawmaterialinventory(rawmaterialinventory);
		rawmaterialinventoryhistory.setCreatedDate(new Timestamp(new Date().getTime()));
		rawmaterialinventoryhistory.setStatus(statusService.getEntityById(Status.class,Long.parseLong(messageSource.getMessage(ERPConstants.STATUS_RAW_MATERIAL_INVENTORY_ADD, null, null))));
		rawmaterialinventoryhistoryService.addEntity(rawmaterialinventoryhistory);
	}
	
	private void addOrderHistory(Rawmaterialorderinvoice rawmaterialorderinvoice,Rawmaterialorder rawmaterialorder) throws Exception{
		Rawmaterialorderhistory rawmaterialorderhistory = new Rawmaterialorderhistory();
		rawmaterialorderhistory.setComment(rawmaterialorderinvoice.getDescription());
		rawmaterialorderhistory.setRawmaterialorder(rawmaterialorder);
		rawmaterialorderhistory.setRawmaterialorderinvoice(rawmaterialorderinvoice);
		rawmaterialorderhistory.setCreatedDate(new Timestamp(new Date().getTime()));
		rawmaterialorderhistory.setIsactive(true);
		rawmaterialorderhistory.setQualitycheckrawmaterial(null);
		rawmaterialorderhistory.setStatus1(statusService.getEntityById(Status.class,rawmaterialorder.getStatus().getId()));
		rawmaterialorderhistory.setStatus2(statusService.getEntityById(Status.class, getOrderStatus(rawmaterialorder)));
		rawmaterialorderhistory.setCreatedBy(3);
		rawmaterialorderhistoryService.addEntity(rawmaterialorderhistory);
	}
	private void updateRMIdQuantityMap(long rmId,int quantity){
		if(rmIdQuantityMap == null){
			rmIdQuantityMap = new HashMap<Long, Integer>();
		}
		rmIdQuantityMap.put(rmId, quantity);
	
	}

	@RequestMapping(value = "listrm/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Rmorderinvoiceintakquantity> getRmorderinvoiceintakquantityByRMOInvoiceId(
			@PathVariable("id") long id) {
		List<Rmorderinvoiceintakquantity> rmorderinvoiceintakquantities = null;
		try {
			rmorderinvoiceintakquantities = rmorderinvoiceintakquantityService.getRmorderinvoiceintakquantityByRMOInvoiceId(id);
			System.out.println("list size "
					+ rmorderinvoiceintakquantities.size());
			for (Rmorderinvoiceintakquantity rmorderinvoiceintakquantity : rmorderinvoiceintakquantities) {
				Rawmaterial rawmaterial = rawmaterialService.getEntityById(
						Rawmaterial.class, rmorderinvoiceintakquantity
								.getRawmaterial().getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rmorderinvoiceintakquantities;
	}

}
