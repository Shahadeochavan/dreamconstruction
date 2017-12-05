package com.nextech.dreamConstruction.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nextech.dreamConstruction.dto.PurchaseAssnDTO;
import com.nextech.dreamConstruction.dto.PurchaseDTO;
import com.nextech.dreamConstruction.model.Productinventory;
import com.nextech.dreamConstruction.model.Purchase;
import com.nextech.dreamConstruction.model.PurchaseAssn;
import com.nextech.dreamConstruction.model.Status;
import com.nextech.dreamConstruction.service.ProductinventoryService;
import com.nextech.dreamConstruction.service.PurchaseAssnService;
import com.nextech.dreamConstruction.service.PurchaseService;
import com.nextech.dreamConstruction.service.StatusService;
import com.nextech.dreamConstruction.status.UserStatus;

@Controller
@RequestMapping("/purchaseOrderIn")
public class PurchaseOrderIn {

	@Autowired
	ProductinventoryService productinventoryService;
	
	@Autowired
	PurchaseService purchaseService;
	
	@Autowired
	StatusService statusService;
	
	@Autowired
	PurchaseAssnService purchaseAssnService;
	
	@Autowired
	static Logger logger = Logger.getLogger(PurchaseOrderIn.class);
	
	private static final int PURCHASE_ORDER_INCOMPLETE = 80;
	private static final int PURCHASE_ORDER_COMPLETE = 79;
	
	@RequestMapping(value = "/inventoryIn", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus inventoryIn(@RequestBody PurchaseDTO purchaseDTO, BindingResult bindingResult,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
	 for(PurchaseAssnDTO purchaseAssnDTO :purchaseDTO.getPurchaseAssnDTOs()){
				
				updateProductInvetory(purchaseAssnDTO);
				
				updatePurchaseOrderAssoRemainingQuantity(purchaseDTO, purchaseAssnDTO, request, response);
				
				updatePurchaseOrder(purchaseDTO);
			}
			return new UserStatus(1, "Product Inventory Store In added Successfully !");
		} catch (ConstraintViolationException cve) {
			logger.error(cve);
			cve.printStackTrace();
			return new UserStatus(0, cve.getCause().getMessage());
		} catch (PersistenceException pe) {
			logger.error(pe);
			pe.printStackTrace();
			return new UserStatus(0, pe.getCause().getMessage());
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return new UserStatus(0, e.getCause().getMessage());
		}
	}
	
	private void updatePurchaseOrderAssoRemainingQuantity(PurchaseDTO purchaseDTO, PurchaseAssnDTO purchaseAssnDTO,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PurchaseAssn purchaseAssn = purchaseAssnService.getPurchaseAssnByPurchaseIdAndProductId(purchaseDTO.getPurchaseId(), purchaseAssnDTO.getProductId().getId());
		if(purchaseAssn.getRemainingQuantity()>=purchaseAssnDTO.getQuantity()){
			purchaseAssn.setRemainingQuantity(purchaseAssn.getRemainingQuantity() - purchaseAssnDTO.getQuantity());
			purchaseAssnService.updateEntity(purchaseAssn);
		}
	}
	
	private void updatePurchaseOrder(PurchaseDTO purchaseDTO)
			throws Exception {
		Purchase purchase = purchaseService.getEntityById(Purchase.class, purchaseDTO.getPurchaseId());
		purchase.setStatus(statusService.getEntityById(Status.class,getPurchesOrderStatus(purchase)));
		purchase.setCreatedDate(new Timestamp(new Date().getTime()));
		purchaseService.updateEntity(purchase);
	}
	
	private int getPurchesOrderStatus(Purchase purchase)
			throws Exception {
		boolean isOrderComplete = false;
		List<PurchaseAssn> purchaseAssns = purchaseAssnService.getPurchaseAssnByPurchaseId(purchase.getId());
		for (Iterator<PurchaseAssn> iterator = purchaseAssns
				.iterator(); iterator.hasNext();) {
			PurchaseAssn purchaseAssn = (PurchaseAssn) iterator.next();
			if (purchaseAssn.getRemainingQuantity() == 0) {
				isOrderComplete = true;
			} else {
				isOrderComplete = false;
				break;
			}
		}
		return isOrderComplete ? PURCHASE_ORDER_COMPLETE: PURCHASE_ORDER_INCOMPLETE;
	}
	
	private Productinventory updateProductInvetory(PurchaseAssnDTO purchaseAssnDTO) throws Exception{
		Productinventory productinventory = productinventoryService.getProductinventoryByProductId(purchaseAssnDTO.getProductId().getId());
		if(productinventory !=null){
		productinventory.setQuantityavailable(productinventory.getQuantityavailable()+purchaseAssnDTO.getQuantity());
		productinventoryService.updateEntity(productinventory);
			
		}
		return productinventory;
		
	}
}
