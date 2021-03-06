package com.nextech.systeminventory.service;

import java.util.List;

import com.nextech.systeminventory.model.Productorder;

public interface ProductorderService extends CRUDService<Productorder>{

	public Productorder getProductorderByProductOrderId(long pOrderId) throws Exception;

	public List<Productorder> getPendingProductOrders(long statusId,long statusId1);

	public List<Productorder> getInCompleteProductOrder(long clientId,long statusId,long statusId1);
	
	public List<Productorder> getProductPrderByClientId(long clientId) throws Exception;
}
