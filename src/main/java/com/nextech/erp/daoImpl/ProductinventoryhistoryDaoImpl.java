package com.nextech.erp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.ProductinventoryhistoryDao;
import com.nextech.erp.model.Productinventoryhistory;

public class ProductinventoryhistoryDaoImpl implements
		ProductinventoryhistoryDao {
	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addProductinventoryhistory(
			Productinventoryhistory productinventoryhistory) {
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(productinventoryhistory);
			tx.commit();
			session.close();
		} catch (ConstraintViolationException cve) {
			System.out.println("Inside addProductinventoryhistory");
			cve.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Productinventoryhistory getProductinventoryhistoryById(long id)
			throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session
				.createCriteria(Productinventoryhistory.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("id", id));
		Productinventoryhistory Productinventoryhistory = criteria.list()
				.size() > 0 ? (Productinventoryhistory) criteria.list().get(0)
				: null;
		session.close();
		return Productinventoryhistory;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Productinventoryhistory> getProductinventoryhistoryList()
			throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session
				.createCriteria(Productinventoryhistory.class);
		criteria.add(Restrictions.eq("isactive", true));
		List<Productinventoryhistory> productinventoryhistoryList = criteria
				.list();
		session.close();
		return productinventoryhistoryList;
	}

	@Override
	public boolean deleteProductinventoryhistory(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Productinventoryhistory.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}

	@Override
	public Productinventoryhistory updateProductinventoryhistory(
			Productinventoryhistory productinventoryhistory) throws Exception {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(productinventoryhistory);
		session.getTransaction().commit();
		session.close();
		return productinventoryhistory;
	}
}
