package com.nextech.erp.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.ProductinventoryhistoryDao;
import com.nextech.erp.model.Productinventoryhistory;
import com.nextech.erp.service.ProductinventoryhistoryService;

public class ProductinventoryhistoryServiceImpl implements
		ProductinventoryhistoryService {

	@Autowired
	ProductinventoryhistoryDao productinventoryhistoryDao;

	@Override
	public Long addProductinventoryhistory(
			Productinventoryhistory productinventoryhistory) throws Exception {
		productinventoryhistory.setCreatedDate(new Timestamp(new Date()
				.getTime()));
		return productinventoryhistoryDao
				.addProductinventoryhistory(productinventoryhistory);
	}

	@Override
	public Productinventoryhistory getProductinventoryhistoryById(long id)
			throws Exception {
		return productinventoryhistoryDao.getProductinventoryhistoryById(id);
	}

	@Override
	public List<Productinventoryhistory> getProductinventoryhistoryList()
			throws Exception {
		return productinventoryhistoryDao.getProductinventoryhistoryList();
	}

	@Override
	public boolean deleteProductinventoryhistory(long id) throws Exception {
		return productinventoryhistoryDao.deleteProductinventoryhistory(id);
	}

	@Override
	public Productinventoryhistory updateProductinventoryhistory(
			Productinventoryhistory productinventoryhistory) throws Exception {
		productinventoryhistory.setUpdatedDate(new Timestamp(new Date()
				.getTime()));
		return productinventoryhistoryDao
				.updateProductinventoryhistory(productinventoryhistory);
	}

}
