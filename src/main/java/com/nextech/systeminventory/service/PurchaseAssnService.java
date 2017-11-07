package com.nextech.systeminventory.service;

import java.util.List;

import com.nextech.systeminventory.model.PurchaseAssn;

public interface PurchaseAssnService  extends CRUDService<PurchaseAssn>{
	
	public List<PurchaseAssn> getPurchaseAssnByPurchaseId(long purhaseId) throws Exception;
	
	public PurchaseAssn getPurchaseAssnByPurchaseIdAndProductId(long purchaseId,long productId) throws Exception;

}
