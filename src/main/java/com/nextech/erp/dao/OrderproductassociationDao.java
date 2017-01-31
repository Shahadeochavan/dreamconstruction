package com.nextech.erp.dao;

import java.util.List;

import com.nextech.erp.model.Orderproductassociation;

public interface OrderproductassociationDao {
	public boolean addOrderproductassociation(
			Orderproductassociation orderproductassociation) throws Exception;

	public Orderproductassociation getOrderproductassociationById(long id)
			throws Exception;

	public List<Orderproductassociation> getOrderproductassociationList()
			throws Exception;

	public boolean deleteOrderproductassociation(long id) throws Exception;

	public Orderproductassociation updateOrderproductassociation(
			Orderproductassociation orderproductassociation) throws Exception;
}
