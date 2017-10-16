package com.nextech.systeminventory.dao;

import java.util.List;

import com.nextech.systeminventory.model.Productorderassociation;


public interface ProductorderassociationDao extends SuperDao<Productorderassociation>{
	public Productorderassociation getProductorderassociationByProdcutOrderIdandProdcutId(
			long pOrderId,long pId) throws Exception;

	public List<Productorderassociation> getProductorderassociationByProdcutId(long pId) throws Exception;
	public List<Productorderassociation> getProductorderassociationByOrderId(long oderID) throws Exception;

	public List<Productorderassociation> getIncompleteProductOrderAssoByProdutId(long productId) throws Exception;


	public List<Productorderassociation> getProductOrderAssoByOrderId(long orderId) throws Exception;
	
	public Productorderassociation getProdcutAssoByProdcutId(long prodcutId) throws Exception;
	
	public Productorderassociation getProdcutAssoByOrder(long orderId) throws Exception;
}
