package com.nextech.systeminventory.dao;

import java.util.List;

import com.nextech.systeminventory.model.PurchaseAssn;

public interface PurchaseAssnDao extends SuperDao<PurchaseAssn>{
	
	public List<PurchaseAssn> getPurchaseAssnByPurchaseId(long purhaseId) throws Exception;
	
	public PurchaseAssn getPurchaseAssnByPurchaseIdAndProductId(long purchaseId,long productId) throws Exception;


}
