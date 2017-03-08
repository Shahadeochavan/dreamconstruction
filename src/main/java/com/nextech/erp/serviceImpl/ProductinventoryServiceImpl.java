package com.nextech.erp.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.ProductinventoryDao;
import com.nextech.erp.model.Productinventory;
import com.nextech.erp.service.ProductinventoryService;

public class ProductinventoryServiceImpl extends CRUDServiceImpl<Productinventory> implements ProductinventoryService {

	@Autowired
	 ProductinventoryDao productinventoryDao;
	@Override
	public Productinventory getProductinventoryByProductId(long productId)
			throws Exception {
		// TODO Auto-generated method stub
		return productinventoryDao.getProductinventoryByProductId(productId);
	}

}
