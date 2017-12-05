package com.nextech.dreamConstruction.service;

import java.util.List;

import com.nextech.dreamConstruction.model.PurchaseAssn;

public interface PurchaseAssnService  extends CRUDService<PurchaseAssn>{
	
	public List<PurchaseAssn> getPurchaseAssnByPurchaseId(long purhaseId) throws Exception;
	
	public PurchaseAssn getPurchaseAssnByPurchaseIdAndProductId(long purchaseId,long productId) throws Exception;

}
