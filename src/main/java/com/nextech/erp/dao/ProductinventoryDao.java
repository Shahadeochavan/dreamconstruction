package com.nextech.erp.dao;

import com.nextech.erp.model.Productinventory;

public interface ProductinventoryDao extends SuperDao<Productinventory>{
	
	public Productinventory getProductinventoryByProductId(long productId) throws Exception;
	
}
