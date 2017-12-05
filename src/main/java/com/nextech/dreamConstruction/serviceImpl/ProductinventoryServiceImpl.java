package com.nextech.dreamConstruction.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextech.dreamConstruction.dao.ProductinventoryDao;
import com.nextech.dreamConstruction.model.Productinventory;
import com.nextech.dreamConstruction.service.ProductinventoryService;
@Service
public class ProductinventoryServiceImpl extends CRUDServiceImpl<Productinventory> implements ProductinventoryService {

	@Autowired
	 ProductinventoryDao productinventoryDao;
	@Override
	public Productinventory getProductinventoryByProductId(long productId)
			throws Exception {
		return productinventoryDao.getProductinventoryByProductId(productId);
	}
	@Override
	public List<Productinventory> getProductinventoryListByProductId(
			long productId) throws Exception {
		return productinventoryDao.getProductinventoryListByProductId(productId);
	}

}
