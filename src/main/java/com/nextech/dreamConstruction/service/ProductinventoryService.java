package com.nextech.dreamConstruction.service;

import java.util.List;

import com.nextech.dreamConstruction.model.Productinventory;

public interface ProductinventoryService extends CRUDService<Productinventory>{
	
	public Productinventory getProductinventoryByProductId(long productId) throws Exception;
	
	public List<Productinventory> getProductinventoryListByProductId(long productId) throws Exception;
	
}