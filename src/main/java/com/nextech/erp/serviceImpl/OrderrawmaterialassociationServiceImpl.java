package com.nextech.erp.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.OrderrawmaterialassociationDao;
import com.nextech.erp.model.Orderrawmaterialassociation;
import com.nextech.erp.service.OrderrawmaterialassociationService;

public class OrderrawmaterialassociationServiceImpl implements
		OrderrawmaterialassociationService {

	@Autowired
	OrderrawmaterialassociationDao orderrawmaterialassociationDao;

	@Override
	public boolean addOrderrawmaterialassociation(
			Orderrawmaterialassociation orderrawmaterialassociation)
			throws Exception {
		orderrawmaterialassociation.setCreatedDate(new Timestamp(new Date()
				.getTime()));
		return orderrawmaterialassociationDao
				.addOrderrawmaterialassociation(orderrawmaterialassociation);
	}

	@Override
	public Orderrawmaterialassociation getOrderrawmaterialassociationById(
			long id) throws Exception {
		return orderrawmaterialassociationDao
				.getOrderrawmaterialassociationById(id);
	}

	@Override
	public List<Orderrawmaterialassociation> getOrderrawmaterialassociationList()
			throws Exception {
		return orderrawmaterialassociationDao
				.getOrderrawmaterialassociationList();
	}

	@Override
	public boolean deleteOrderrawmaterialassociation(long id) throws Exception {
		return orderrawmaterialassociationDao
				.deleteOrderrawmaterialassociation(id);
	}

	@Override
	public Orderrawmaterialassociation updateOrderrawmaterialassociation(
			Orderrawmaterialassociation orderrawmaterialassociation)
			throws Exception {
		orderrawmaterialassociation.setUpdatedDate(new Timestamp(new Date()
				.getTime()));
		return orderrawmaterialassociationDao
				.updateOrderrawmaterialassociation(orderrawmaterialassociation);
	}

}
