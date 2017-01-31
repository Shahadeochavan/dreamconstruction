package com.nextech.erp.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.nextech.erp.dao.OrderproductassociationDao;
import com.nextech.erp.model.Orderproductassociation;
import com.nextech.erp.service.OrderproductassociationService;

public class OrderproductassociationServiceImpl implements
		OrderproductassociationService {
	@Autowired
	OrderproductassociationDao orderproductassociationDao;

	@Override
	public boolean addOrderproductassociation(
			Orderproductassociation orderproductassociation) throws Exception {
		orderproductassociation.setCreatedDate(new Timestamp(new Date()
				.getTime()));
		return orderproductassociationDao
				.addOrderproductassociation(orderproductassociation);
	}

	@Override
	public Orderproductassociation getOrderproductassociationById(long id)
			throws Exception {
		return orderproductassociationDao.getOrderproductassociationById(id);
	}

	@Override
	public List<Orderproductassociation> getOrderproductassociationList()
			throws Exception {
		return orderproductassociationDao.getOrderproductassociationList();
	}

	@Override
	public boolean deleteOrderproductassociation(long id) throws Exception {
		return orderproductassociationDao.deleteOrderproductassociation(id);
	}

	@Override
	public Orderproductassociation updateOrderproductassociation(
			Orderproductassociation orderproductassociation) throws Exception {
		orderproductassociation.setUpdatedDate(new Timestamp(new Date()
				.getTime()));
		return orderproductassociationDao
				.updateOrderproductassociation(orderproductassociation);
	}

}
