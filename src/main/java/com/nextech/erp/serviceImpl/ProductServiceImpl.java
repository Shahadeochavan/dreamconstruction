package com.nextech.erp.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.ProductDao;
import com.nextech.erp.model.Product;
import com.nextech.erp.service.ProductService;

public class ProductServiceImpl extends CRUDServiceImpl<Product> implements ProductService {

	@Autowired
	ProductDao productDao;

	@Override
	public Product getProductByName(String name) throws Exception {
		return productDao.getProductByName(name);
	}

	@Override
	public Product getProductByPartNumber(String partnumber) throws Exception {
		return productDao.getProductByPartNumber(partnumber);
	}
}
