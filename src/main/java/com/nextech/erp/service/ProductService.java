package com.nextech.erp.service;

import com.nextech.erp.model.Product;

public interface ProductService extends CRUDService<Product>{

	public Product getProductByName(String name) throws Exception;
	
	public Product getProductByPartNumber(String partnumber) throws Exception;
	
	public boolean isOrderPartiallyDispatched(long orderId) throws Exception;

	public Product getProductListByProductId(long id);
}
