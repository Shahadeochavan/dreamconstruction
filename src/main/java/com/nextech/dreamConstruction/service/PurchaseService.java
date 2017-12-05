package com.nextech.dreamConstruction.service;

import java.util.List;

import com.nextech.dreamConstruction.model.Purchase;

public interface PurchaseService extends CRUDService<Purchase>{
	
	public List<Purchase> getPendingPurchaseOrders(long statusId,long statusId1);

}
