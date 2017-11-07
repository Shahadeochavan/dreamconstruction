package com.nextech.systeminventory.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nextech.systeminventory.constants.ERPConstants;
import com.nextech.systeminventory.dto.ProductDTO;
import com.nextech.systeminventory.dto.ProductOrderAssociationDTO;
import com.nextech.systeminventory.dto.PurchaseAssnDTO;
import com.nextech.systeminventory.dto.PurchaseDTO;
import com.nextech.systeminventory.factory.PurchaseRequestResponseFactory;
import com.nextech.systeminventory.factory.PurhcaseAssnRequestResponseFactory;
import com.nextech.systeminventory.model.Productinventory;
import com.nextech.systeminventory.model.Productorder;
import com.nextech.systeminventory.model.Productorderassociation;
import com.nextech.systeminventory.model.Purchase;
import com.nextech.systeminventory.model.PurchaseAssn;
import com.nextech.systeminventory.service.ProductinventoryService;
import com.nextech.systeminventory.service.PurchaseAssnService;
import com.nextech.systeminventory.service.PurchaseService;
import com.nextech.systeminventory.status.UserStatus;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired
	PurchaseService purchaseService;
	
	@Autowired
	PurchaseAssnService purchaseAssnService;
	
	@Autowired
	ProductinventoryService productinventoryService;

	@RequestMapping(value = "/createMultiple", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addPurchase(@Valid @RequestBody PurchaseDTO purchaseDTO,
			BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
		long id =	purchaseService.addEntity(PurchaseRequestResponseFactory.setPurchase(purchaseDTO));
		for (PurchaseAssnDTO purchaseAssnDTO : purchaseDTO.getPurchaseAssnDTOs()) {
			purchaseAssnDTO.setPurchaseId(id);
			purchaseAssnService.addEntity(PurhcaseAssnRequestResponseFactory.setPurchaseAssn(purchaseAssnDTO));
		}
			return new UserStatus(1, "Purchase added Successfully !");
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
	public @ResponseBody Purchase getPurchase(@PathVariable("id") long id) {
		Purchase Purchase = null;
		try {
			Purchase = purchaseService.getEntityById(Purchase.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Purchase;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateUserType(
			@RequestBody Purchase Purchase,HttpServletRequest request,HttpServletResponse response) {
		try {
			Purchase.setIsactive(true);
			purchaseService.updateEntity(Purchase);
			return new UserStatus(1, "Purchase update Successfully !");
		} catch (Exception e) {
			 e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Purchase> getPurchase() {

		List<Purchase> Purchases = null;
		try {
			Purchases = purchaseService.getEntityList(Purchase.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Purchases;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody UserStatus deletePurchase(@PathVariable("id") long id) {

		try {
			Purchase Purchase = purchaseService.getEntityById(Purchase.class,id);
			Purchase.setIsactive(false);
			purchaseService.updateEntity(Purchase);
			return new UserStatus(1, "Purchase deleted Successfully !");
		} catch (Exception e) {
			return new UserStatus(0, e.toString());
		}

	}
	@RequestMapping(value = "purchaseId/{orderId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<PurchaseAssnDTO> getProductOrder(
			@PathVariable("orderId") long id) {
		List<PurchaseAssn> purchaseAssns = null;
		List<PurchaseAssnDTO> purchaseAssnDTOs = new ArrayList<PurchaseAssnDTO>();
		try {
			purchaseAssns = purchaseAssnService.getPurchaseAssnByPurchaseId(id);
			for (PurchaseAssn purchaseAssn : purchaseAssns) {
				PurchaseAssnDTO  purchaseAssnDTO =  new PurchaseAssnDTO();
				//Productinventory productinventory = productinventoryService.getProductinventoryByProductId(purchaseAssn.getProduct().getId());
				ProductDTO productDTO =  new ProductDTO();
				productDTO.setId(purchaseAssn.getProduct().getId());
				productDTO.setPartNumber(purchaseAssn.getProduct().getPartNumber());
				purchaseAssnDTO.setProductId(productDTO);
				purchaseAssnDTO.setQuantity(purchaseAssn.getQuantity());
				purchaseAssnDTOs.add(purchaseAssnDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return purchaseAssnDTOs;
	}
	
	@RequestMapping(value = "/pendingList", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Purchase> getPendingsProductorders() {

		List<Purchase> purchases = null;
		try {
			// TODO afterwards you need to change it from properties.
			purchases = purchaseService.getPendingPurchaseOrders(78, 80);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return purchases;
	}
}
