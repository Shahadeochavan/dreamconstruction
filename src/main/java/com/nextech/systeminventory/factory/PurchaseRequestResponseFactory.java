package com.nextech.systeminventory.factory;

import com.nextech.systeminventory.dto.PurchaseDTO;
import com.nextech.systeminventory.model.Purchase;
import com.nextech.systeminventory.model.Status;
import com.nextech.systeminventory.model.Vendor;

public class PurchaseRequestResponseFactory {

	public static Purchase setPurchase(PurchaseDTO purchaseDTO){
		Purchase purchase = new Purchase();
		purchase.setName(purchaseDTO.getName());
		purchase.setDescription(purchaseDTO.getDescription());
		purchase.setExpecteddeliveryDate(purchaseDTO.getExpectedDeliveryDate());
		purchase.setQuantity(purchaseDTO.getPurchaseAssnDTOs().size());
		Status status =  new Status();
		status.setId(78);
		purchase.setStatus(status);
		purchase.setIsactive(true);
		purchase.setCreatedBy("1");
		purchase.setUpdatedBy("1");
		Vendor vendor = new Vendor();
		vendor.setId(purchaseDTO.getVendorId());
		purchase.setVendor(vendor);
		purchase.setId(purchaseDTO.getId());
		return purchase;
		
	}
}
