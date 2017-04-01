package com.nextech.erp.daoImpl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.nextech.erp.dao.ProductDao;
import com.nextech.erp.model.Product;

public class ProductDaoImpl extends SuperDaoImpl<Product> implements ProductDao {

	@Override
	public Product getProductByName(String productname) throws Exception {
		session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("name", productname));
		Product product = criteria.list().size() > 0 ? (Product) criteria.list().get(0) : null;
		return product;
	}

	@Override
	public Product getProductByPartNumber(String partNumber) throws Exception {
		session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("partNumber", partNumber));
		Product product = criteria.list().size() > 0 ? (Product) criteria.list().get(0) : null;
		return product;
	}

}
