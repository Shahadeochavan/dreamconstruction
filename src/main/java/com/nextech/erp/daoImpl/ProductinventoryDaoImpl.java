package com.nextech.erp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.ProductinventoryDao;
import com.nextech.erp.model.Productinventory;

public class ProductinventoryDaoImpl implements ProductinventoryDao {
	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addProductinventory(Productinventory productinventory) {
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(productinventory);
			tx.commit();
			session.close();
		} catch (ConstraintViolationException cve) {
			System.out.println("Inside addProductinventory");
			cve.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Productinventory getProductinventoryById(long id) throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Productinventory.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("id", id));
		Productinventory productinventory = criteria.list().size() > 0 ? (Productinventory) criteria
				.list().get(0) : null;
		session.close();
		return productinventory;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Productinventory> getProductinventoryList() throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Productinventory.class);
		criteria.add(Restrictions.eq("isactive", true));
		List<Productinventory> productinventoryList = criteria.list();
		session.close();
		return productinventoryList;
	}

	@Override
	public boolean deleteProductinventory(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Productinventory.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}

	@Override
	public Productinventory updateProductinventory(
			Productinventory productinventory) throws Exception {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(productinventory);
		session.getTransaction().commit();
		session.close();
		return productinventory;
	}
}
