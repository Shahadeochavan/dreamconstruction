package com.nextech.erp.service;

import java.util.List;

import com.nextech.erp.model.Productorderassociation;

public interface ProductorderassociationService extends CRUDService<Productorderassociation>{
	
	public Productorderassociation getProductorderassociationByProdcutOrderIdandProdcutId(
			long pOrderId,long pId) throws Exception;
	
	public List<Productorderassociation> getProductorderassociationByProdcutId(long pId) throws Exception;
	public List<Productorderassociation> getProductorderassociationByOrderId(long orderId) throws Exception;
	
	public Productorderassociation getProductOrderAssoByProdutId(long productId) throws Exception;
}
