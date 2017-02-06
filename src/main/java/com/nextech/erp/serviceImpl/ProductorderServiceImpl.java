package com.nextech.erp.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.ProductorderDao;
import com.nextech.erp.model.Productorder;
import com.nextech.erp.service.ProductorderService;

public class ProductorderServiceImpl implements ProductorderService {

	@Autowired
	ProductorderDao productorderDao;

	@Override
	public Long addProductorder(Productorder productorder) throws Exception {
		productorder.setCreatedDate(new Timestamp(new Date().getTime()));
		return productorderDao.addProductorder(productorder);
	}

	@Override
	public Productorder getProductorderById(long id) throws Exception {
		return productorderDao.getProductorderById(id);
	}

	@Override
	public List<Productorder> getProductorderList() throws Exception {
		return productorderDao.getProductorderList();
	}

	@Override
	public boolean deleteProductorder(long id) throws Exception {
		return productorderDao.deleteProductorder(id);
	}

	@Override
	public Productorder updateProductorder(Productorder productorder)
			throws Exception {
		productorder.setUpdatedDate(new Timestamp(new Date().getTime()));
		return productorderDao.updateProductorder(productorder);
	}

}
