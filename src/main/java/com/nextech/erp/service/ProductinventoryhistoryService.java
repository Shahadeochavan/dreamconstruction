package com.nextech.erp.service;

import java.util.List;

import com.nextech.erp.model.Productinventoryhistory;

public interface ProductinventoryhistoryService {
	public boolean addProductinventoryhistory(
			Productinventoryhistory productinventoryhistory) throws Exception;

	public Productinventoryhistory getProductinventoryhistoryById(long id)
			throws Exception;

	public List<Productinventoryhistory> getProductinventoryhistoryList()
			throws Exception;

	public boolean deleteProductinventoryhistory(long id) throws Exception;

	public Productinventoryhistory updateProductinventoryhistory(
			Productinventoryhistory productinventoryhistory) throws Exception;
}

