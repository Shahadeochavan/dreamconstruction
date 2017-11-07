package com.nextech.systeminventory.dao;

import java.util.List;

import com.nextech.systeminventory.model.Purchase;

public interface PurchaseDao extends SuperDao<Purchase>{
	
	public List<Purchase> getPendingPurchaseOrders(long statusId,long statusId1);

}
