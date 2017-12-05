package com.nextech.dreamConstruction.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextech.dreamConstruction.dao.ProductorderDao;
import com.nextech.dreamConstruction.model.Productorder;
import com.nextech.dreamConstruction.service.ProductorderService;
@Service
public class ProductorderServiceImpl extends CRUDServiceImpl<Productorder> implements ProductorderService {

	@Autowired
	ProductorderDao productorderDao;
	
	@Override
	public Productorder getProductorderByProductOrderId(long pOrderId)
			throws Exception {
		return productorderDao.getProductorderByProductOrderId(pOrderId);
	}
	@Override
	public List<Productorder> getPendingProductOrders(long statusId,long statusId1) {
		return productorderDao.getPendingProductOrders(statusId,statusId1);
	}
	@Override
	public List<Productorder> getInCompleteProductOrder(long clientId,long statusId,long statusId1) {
		return productorderDao.getInCompleteProductOrder(clientId,statusId,statusId1);
	}
	@Override
	public List<Productorder> getProductPrderByClientId(long clientId)
			throws Exception {
		return null;
	}
}
