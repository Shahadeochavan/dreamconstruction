package com.nextech.dreamConstruction.factory;

import javax.servlet.http.HttpServletRequest;

import com.nextech.dreamConstruction.dto.PurchaseDTO;
import com.nextech.dreamConstruction.model.Purchase;
import com.nextech.dreamConstruction.model.Status;
import com.nextech.dreamConstruction.model.Vendor;

public class PurchaseRequestResponseFactory {

	public static Purchase setPurchase(PurchaseDTO purchaseDTO,HttpServletRequest request){
		Purchase purchase = new Purchase();
		purchase.setName(purchaseDTO.getName());
		purchase.setDescription(purchaseDTO.getDescription());
		purchase.setExpecteddeliveryDate(purchaseDTO.getExpectedDeliveryDate());
		purchase.setQuantity(purchaseDTO.getPurchaseAssnDTOs().size());
		Status status =  new Status();
		status.setId(78);
		purchase.setStatus(status);
		purchase.setIsactive(true);
		purchase.setCreatedBy(request.getAttribute("current_user").toString());
		Vendor vendor = new Vendor();
		vendor.setId(purchaseDTO.getVendorId());
		purchase.setVendor(vendor);
		purchase.setId(purchaseDTO.getId());
		return purchase;
		
	}
}
