package com.nextech.systeminventory.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextech.systeminventory.dao.ProductinventoryDao;
import com.nextech.systeminventory.model.Productinventory;
import com.nextech.systeminventory.service.ProductinventoryService;
@Service
public class ProductinventoryServiceImpl extends CRUDServiceImpl<Productinventory> implements ProductinventoryService {

	@Autowired
	 ProductinventoryDao productinventoryDao;
	@Override
	public Productinventory getProductinventoryByProductId(long productId)
			throws Exception {
		// TODO Auto-generated method stub
		return productinventoryDao.getProductinventoryByProductId(productId);
	}
	@Override
	public List<Productinventory> getProductinventoryListByProductId(
			long productId) throws Exception {
		// TODO Auto-generated method stub
		return productinventoryDao.getProductinventoryListByProductId(productId);
	}

}
