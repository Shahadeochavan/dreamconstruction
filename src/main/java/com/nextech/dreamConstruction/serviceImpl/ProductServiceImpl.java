package com.nextech.dreamConstruction.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextech.dreamConstruction.dao.ProductDao;
import com.nextech.dreamConstruction.dao.ProductorderDao;
import com.nextech.dreamConstruction.dto.ProductDTO;
import com.nextech.dreamConstruction.factory.ProductRequestResponseFactory;
import com.nextech.dreamConstruction.model.Product;
import com.nextech.dreamConstruction.service.ProductService;
@Service
public class ProductServiceImpl extends CRUDServiceImpl<Product> implements ProductService {

	@Autowired
	ProductDao productDao;

	@Autowired
	ProductorderDao productorderDao; 
	
	@Override
	public Product getProductByName(String name) throws Exception {
		return productDao.getProductByName(name);
	}

	@Override
	public Product getProductByPartNumber(String partnumber) throws Exception {
		return productDao.getProductByPartNumber(partnumber);
	}

	@Override
	public Product getProductListByProductId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductList(List<Long> productIdList) {
		// TODO Auto-generated method stub
		return productDao.getProductList(productIdList);
	}
	@Override
	public ProductDTO getProductDTO(long id) throws Exception {
		Product product = productDao.getById(Product.class, id);
		if(product ==null){
			return null;
		}
		ProductDTO productDTO = ProductRequestResponseFactory.setProductDto(product);
		return productDTO;
	}
}
