package com.nextech.erp.dao;

import java.util.List;

import com.nextech.erp.model.Productorderassociation;

public interface ProductorderassociationDao extends SuperDao<Productorderassociation>{
	public Productorderassociation getProductorderassociationByProdcutOrderIdandProdcutId(
			long pOrderId,long pId) throws Exception;
	
	public List<Productorderassociation> getProductorderassociationByProdcutId(long pId) throws Exception;
	public List<Productorderassociation> getProductorderassociationByOrderId(long oderID) throws Exception;
}
