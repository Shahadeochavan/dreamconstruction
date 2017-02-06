package com.nextech.erp.dao;

import java.util.List;

import com.nextech.erp.model.Productorderassociation;

public interface ProductorderassociationDao {
	public Long addProductorderassociation(
			Productorderassociation productorderassociation) throws Exception;

	public Productorderassociation getProductorderassociationById(long id)
			throws Exception;

	public List<Productorderassociation> getProductorderassociationList()
			throws Exception;

	public boolean deleteProductorderassociation(long id) throws Exception;

	public Productorderassociation updateProductorderassociation(
			Productorderassociation productorderassociation) throws Exception;
	
	
}
