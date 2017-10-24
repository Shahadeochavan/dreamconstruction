package com.nextech.systeminventory.service;

import java.util.List;

import com.nextech.systeminventory.dto.ProductDTO;
import com.nextech.systeminventory.model.Product;
public interface ProductService extends CRUDService<Product>{

	public Product getProductByName(String name) throws Exception;
	
	public Product getProductByPartNumber(String partnumber) throws Exception;
	
	public Product getProductListByProductId(long id);
	
	public List<Product> getProductList(List<Long> productIdList);
	
	public ProductDTO getProductDTO(long id) throws Exception;
}
