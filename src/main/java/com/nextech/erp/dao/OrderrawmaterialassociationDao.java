package com.nextech.erp.dao;

import java.util.List;

import com.nextech.erp.model.Orderrawmaterialassociation;

public interface OrderrawmaterialassociationDao {
	public boolean addOrderrawmaterialassociation(
			Orderrawmaterialassociation orderrawmaterialassociation)
			throws Exception;

	public Orderrawmaterialassociation getOrderrawmaterialassociationById(
			long id) throws Exception;

	public List<Orderrawmaterialassociation> getOrderrawmaterialassociationList()
			throws Exception;

	public boolean deleteOrderrawmaterialassociation(long id) throws Exception;

	public Orderrawmaterialassociation updateOrderrawmaterialassociation(
			Orderrawmaterialassociation orderrawmaterialassociation)
			throws Exception;
}
