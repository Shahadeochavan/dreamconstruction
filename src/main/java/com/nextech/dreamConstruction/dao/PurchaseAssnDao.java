package com.nextech.dreamConstruction.dao;

import java.util.List;

import com.nextech.dreamConstruction.model.PurchaseAssn;

public interface PurchaseAssnDao extends SuperDao<PurchaseAssn>{
	
	public List<PurchaseAssn> getPurchaseAssnByPurchaseId(long purhaseId) throws Exception;
	
	public PurchaseAssn getPurchaseAssnByPurchaseIdAndProductId(long purchaseId,long productId) throws Exception;


}
