package com.nextech.erp.service;

import java.util.List;

import com.nextech.erp.model.Productinventory;

public interface ProductinventoryService {
	public Long addProductinventory(Productinventory Productinventory) throws Exception;

	public Productinventory getProductinventoryById(long id) throws Exception;

	public List<Productinventory> getProductinventoryList() throws Exception;

	public boolean deleteProductinventory(long id) throws Exception;

	public Productinventory updateProductinventory(Productinventory Productinventory) throws Exception;
}