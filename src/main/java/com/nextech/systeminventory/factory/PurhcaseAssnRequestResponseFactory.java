package com.nextech.systeminventory.factory;

import com.nextech.systeminventory.dto.PurchaseAssnDTO;
import com.nextech.systeminventory.model.Product;
import com.nextech.systeminventory.model.Purchase;
import com.nextech.systeminventory.model.PurchaseAssn;

public class PurhcaseAssnRequestResponseFactory {
	
	public static PurchaseAssn setPurchaseAssn(PurchaseAssnDTO purchaseAssnDTO){
		PurchaseAssn purchaseAssn =  new PurchaseAssn();
		Product product =  new  Product();
		product.setId(purchaseAssnDTO.getProductId().getId());
		purchaseAssn.setProduct(product);
		Purchase purchase  =  new Purchase();
		purchase.setId(purchaseAssnDTO.getPurchaseId());
		purchaseAssn.setPurchase(purchase);
		purchaseAssn.setQuantity(purchaseAssnDTO.getQuantity());
		purchaseAssn.setRemainingQuantity(purchaseAssnDTO.getQuantity());
		purchaseAssn.setIsactive(true);
		purchaseAssn.setCreatedBy("1");
		purchaseAssn.setUpdatedBy("1");
		return purchaseAssn;
	}

}
