package com.nextech.systeminventory.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextech.systeminventory.dao.PurchaseAssnDao;
import com.nextech.systeminventory.model.PurchaseAssn;
import com.nextech.systeminventory.service.PurchaseAssnService;

@Service
public class PurchaseAssnServiceImpl extends CRUDServiceImpl<PurchaseAssn> implements PurchaseAssnService{

	@Autowired
	PurchaseAssnDao purchaseAssnDao;
	@Override
	public List<PurchaseAssn> getPurchaseAssnByPurchaseId(long purhaseId)
			throws Exception {
		// TODO Auto-generated method stub
		return purchaseAssnDao.getPurchaseAssnByPurchaseId(purhaseId);
	}
	@Override
	public PurchaseAssn getPurchaseAssnByPurchaseIdAndProductId(
			long purchaseId, long productId) throws Exception {
		// TODO Auto-generated method stub
		return purchaseAssnDao.getPurchaseAssnByPurchaseIdAndProductId(purchaseId, productId);
	}

}
