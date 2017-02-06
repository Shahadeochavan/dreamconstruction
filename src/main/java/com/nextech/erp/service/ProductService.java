package com.nextech.erp.service;

import java.util.List;

import com.nextech.erp.model.Product;

public interface ProductService {
	public Long addProduct(Product product) throws Exception;

	public Product getProductById(long id) throws Exception;

	public List<Product> getProductist() throws Exception;

	public boolean deleteProduct(long id) throws Exception;

	public Product updateProduct(Product product) throws Exception;
	
    public Product getProductByName(String name) throws Exception;
	
	public Product getProductByPartNumber(String partnumber) throws Exception;
}
