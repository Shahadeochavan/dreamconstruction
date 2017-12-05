package com.nextech.dreamConstruction.dao;

import java.util.List;

import com.nextech.dreamConstruction.model.Purchase;

public interface PurchaseDao extends SuperDao<Purchase>{
	
	public List<Purchase> getPendingPurchaseOrders(long statusId,long statusId1);

}
