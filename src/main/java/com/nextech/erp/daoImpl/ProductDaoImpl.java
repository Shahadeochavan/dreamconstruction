package com.nextech.erp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.ProductDao;
import com.nextech.erp.model.Product;
import com.nextech.erp.model.Vendor;

public class ProductDaoImpl implements ProductDao {

	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addProduct(Product product) throws Exception {
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(product);
			tx.commit();
			session.close();
		} catch (ConstraintViolationException cve) {
			System.out.println("Inside addRawmaterial");
			cve.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Product getProductById(long id) throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("id", id));
		Product product = criteria.list().size() > 0 ? (Product) criteria
				.list().get(0) : null;
		session.close();
		return product;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Product> getProductist() throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.eq("isactive", true));
		List<Product> productList = criteria.list();
		session.close();
		return productList;
	}

	@Override
	public boolean deleteProduct(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Product.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}

	@Override
	public Product updateProduct(Product product) throws Exception {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(product);
		session.getTransaction().commit();
		session.close();
		return product;
	}

	@Override
	public Product getProductByName(String productname) throws Exception {
		session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("name", productname));
		Product product = criteria.list().size() > 0 ? (Product) criteria
				.list().get(0) : null;
		session.close();
		return product;
	}

	@Override
	public Product getProductByPartNumber(String partNumber) throws Exception {
		session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("partNumber", partNumber));
		Product product = criteria.list().size() > 0 ? (Product) criteria
				.list().get(0) : null;
		session.close();
		return product;
	}

}
