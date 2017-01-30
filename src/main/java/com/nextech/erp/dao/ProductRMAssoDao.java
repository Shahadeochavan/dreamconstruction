package com.nextech.erp.dao;

import java.util.List;
import com.nextech.erp.model.Productrawmaterialassociation;

public interface ProductRMAssoDao {

	public boolean addProductrawmaterialassociation(
			Productrawmaterialassociation productrawmaterialassociation)
			throws Exception;

	public Productrawmaterialassociation getProductrawmaterialassociationById(
			long id) throws Exception;

	public List<Productrawmaterialassociation> getProductrawmaterialassociationList()
			throws Exception;

	public boolean deleteProductrawmaterialassociation(long id)
			throws Exception;

	public Productrawmaterialassociation updateProductrawmaterialassociation(
			Productrawmaterialassociation productrawmaterialassociation)
			throws Exception;
	public Productrawmaterialassociation getPRMAssociationByPidRmid(long pid, long rmid) throws Exception;
}
