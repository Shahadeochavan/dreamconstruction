package com.nextech.systeminventory.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextech.systeminventory.dao.PurchaseDao;
import com.nextech.systeminventory.model.Purchase;
import com.nextech.systeminventory.service.PurchaseService;

@Service
public class PurchaseServiceImpl extends CRUDServiceImpl<Purchase> implements PurchaseService{

	@Autowired
	PurchaseDao purchaseDao;
	@Override
	public List<Purchase> getPendingPurchaseOrders(long statusId, long statusId1) {
		// TODO Auto-generated method stub
		return purchaseDao.getPendingPurchaseOrders(statusId, statusId1);
	}

}
