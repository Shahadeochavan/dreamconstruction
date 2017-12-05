package com.nextech.dreamConstruction.dao;

import java.util.List;

import com.nextech.dreamConstruction.model.Productinventory;

public interface ProductinventoryDao extends SuperDao<Productinventory>{
	
	public Productinventory getProductinventoryByProductId(long productId) throws Exception;
	
	public List<Productinventory> getProductinventoryListByProductId(long productId) throws Exception;
	
}
