package com.nextech.systeminventory.dao;

import java.util.List;

import com.nextech.systeminventory.model.Productinventory;

public interface ProductinventoryDao extends SuperDao<Productinventory>{
	
	public Productinventory getProductinventoryByProductId(long productId) throws Exception;
	
	public List<Productinventory> getProductinventoryListByProductId(long productId) throws Exception;
	
}
