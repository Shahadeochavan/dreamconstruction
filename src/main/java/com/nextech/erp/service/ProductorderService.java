package com.nextech.erp.service;

import java.util.List;

import com.nextech.erp.model.Productorder;

public interface ProductorderService extends CRUDService<Productorder>{
	public Productorder getProductorderByProductOrderId(long pOrderId) throws Exception;
	public List<Productorder> getPendingProductOrders(long statusId); 
}
