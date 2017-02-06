package com.nextech.erp.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.ProductinventoryDao;
import com.nextech.erp.model.Productinventory;
import com.nextech.erp.service.ProductinventoryService;

public class ProductinventoryServiceImpl implements ProductinventoryService {

	@Autowired
	ProductinventoryDao productinventoryDao;

	@Override
	public Long addProductinventory(Productinventory productinventory)
			throws Exception {
		productinventory.setCreatedDate(new Timestamp(new Date().getTime()));
		return productinventoryDao.addProductinventory(productinventory);
	}

	@Override
	public Productinventory getProductinventoryById(long id) throws Exception {
		return productinventoryDao.getProductinventoryById(id);
	}

	@Override
	public List<Productinventory> getProductinventoryList() throws Exception {
		return productinventoryDao.getProductinventoryList();
	}

	@Override
	public boolean deleteProductinventory(long id) throws Exception {
		return productinventoryDao.deleteProductinventory(id);
	}

	@Override
	public Productinventory updateProductinventory(
			Productinventory productinventory) throws Exception {
		productinventory.setUpdatedDate(new Timestamp(new Date().getTime()));
		return productinventoryDao.updateProductinventory(productinventory);
	}

}
