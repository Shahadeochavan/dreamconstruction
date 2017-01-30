package com.nextech.erp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.ProductorderDao;
import com.nextech.erp.model.Productorder;

public class ProductorderDaoImpl implements ProductorderDao {
	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addProductorder(Productorder productorder) {
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(productorder);
			tx.commit();
			session.close();
		} catch (ConstraintViolationException cve) {
			System.out.println("Inside addProductorder");
			cve.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Productorder getProductorderById(long id) throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Productorder.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("id", id));
		Productorder productorder = criteria.list().size() > 0 ? (Productorder) criteria
				.list().get(0) : null;
		session.close();
		return productorder;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Productorder> getProductorderList() throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Productorder.class);
		criteria.add(Restrictions.eq("isactive", true));
		List<Productorder> productorderList = criteria.list();
		session.close();
		return productorderList;
	}

	@Override
	public boolean deleteProductorder(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Productorder.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}

	@Override
	public Productorder updateProductorder(Productorder productorder)
			throws Exception {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(productorder);
		session.getTransaction().commit();
		session.close();
		return productorder;
	}
}
