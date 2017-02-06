package com.nextech.erp.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.nextech.erp.dao.ProductDao;
import com.nextech.erp.model.Product;
import com.nextech.erp.service.ProductService;

public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;

	@Override
	public Long addProduct(Product product) throws Exception {
		product.setCreatedDate(new Timestamp(new Date().getTime()));
		return productDao.addProduct(product);
	}

	@Override
	public Product getProductById(long id) throws Exception {
		return productDao.getProductById(id);
	}

	@Override
	public List<Product> getProductist() throws Exception {
		return productDao.getProductist();
	}

	@Override
	public boolean deleteProduct(long id) throws Exception {
		return productDao.deleteProduct(id);
	}

	@Override
	public Product updateProduct(Product product) throws Exception {
		product.setUpdatedDate(new Timestamp(new Date().getTime()));
		return productDao.updateProduct(product);
	}

	@Override
	public Product getProductByName(String name) throws Exception {
		return productDao.getProductByName(name);
	}

	@Override
	public Product getProductByPartNumber(String partnumber) throws Exception {
		return productDao.getProductByPartNumber(partnumber);
	}
}
