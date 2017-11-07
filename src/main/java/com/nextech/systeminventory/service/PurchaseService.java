package com.nextech.systeminventory.service;

import java.util.List;

import com.nextech.systeminventory.model.Purchase;

public interface PurchaseService extends CRUDService<Purchase>{
	
	public List<Purchase> getPendingPurchaseOrders(long statusId,long statusId1);

}
