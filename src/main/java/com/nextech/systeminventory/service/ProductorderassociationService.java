package com.nextech.systeminventory.service;

import java.util.List;

import com.nextech.systeminventory.model.Productorderassociation;


public interface ProductorderassociationService extends CRUDService<Productorderassociation>{

	public Productorderassociation getProductorderassociationByProdcutOrderIdandProdcutId(
			long pOrderId,long pId) throws Exception;

	public List<Productorderassociation> getProductorderassociationByProdcutId(long pId) throws Exception;
	
	public List<Productorderassociation> getProductorderassociationByOrderId(long orderId) throws Exception;

	public List<Productorderassociation> getIncompleteProductOrderAssoByProdutId(long productId) throws Exception;

	public List<Productorderassociation> getProductOrderAssoByOrderId(long orderId) throws Exception;
	
	public Productorderassociation getProdcutAssoByProdcutId(long prodcutId) throws Exception;
	
}
