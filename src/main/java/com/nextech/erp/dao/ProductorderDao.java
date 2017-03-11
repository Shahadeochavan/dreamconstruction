package com.nextech.erp.dao;

import java.util.List;

import com.nextech.erp.model.Productorder;

public interface ProductorderDao extends SuperDao<Productorder> {
	public Productorder getProductorderByProductOrderId(
			long pOrderId) throws Exception;
	
	public List<Productorder> getPendingProductOrders(long statusId); 

}
