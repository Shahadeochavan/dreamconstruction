package com.nextech.erp.service;

import com.nextech.erp.model.Productinventory;

public interface ProductinventoryService extends CRUDService<Productinventory>{
	
	public Productinventory getProductinventoryByProductId(long productId) throws Exception;
	
}