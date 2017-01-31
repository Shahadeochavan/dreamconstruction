package com.nextech.erp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.OrderproductassociationDao;
import com.nextech.erp.model.Orderproductassociation;

public class OrderproductassociationDaoImpl implements OrderproductassociationDao{
	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addOrderproductassociation(Orderproductassociation orderproductassociation) {
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(orderproductassociation);
			tx.commit();
			session.close();
		} catch (ConstraintViolationException cve) {
			System.out.println("Inside addOrderproductassociation");
			cve.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Orderproductassociation getOrderproductassociationById(long id) throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Orderproductassociation.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("id", id));
		Orderproductassociation orderproductassociation = criteria.list().size() > 0 ? (Orderproductassociation) criteria.list()
				.get(0) : null;
		session.close();
		return orderproductassociation;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Orderproductassociation> getOrderproductassociationList() throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Orderproductassociation.class);
		criteria.add(Restrictions.eq("isactive", true));
		List<Orderproductassociation> orderproductassociationList = criteria.list();
		session.close();
		return orderproductassociationList;
	}

	@Override
	public boolean deleteOrderproductassociation(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Orderproductassociation.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}

	@Override
	public Orderproductassociation updateOrderproductassociation(Orderproductassociation orderproductassociation) throws Exception {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(orderproductassociation);
		session.getTransaction().commit();
		session.close();
		return orderproductassociation;
	}
}

