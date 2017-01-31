package com.nextech.erp.dao;

import java.util.List;

import com.nextech.erp.model.Productorder;

public interface ProductorderDao {

	public Integer addProductorder(Productorder Productorder) throws Exception;

	public Productorder getProductorderById(long id) throws Exception;

	public List<Productorder> getProductorderList() throws Exception;

	public boolean deleteProductorder(long id) throws Exception;

	public Productorder updateProductorder(Productorder Productorder)
			throws Exception;
}
