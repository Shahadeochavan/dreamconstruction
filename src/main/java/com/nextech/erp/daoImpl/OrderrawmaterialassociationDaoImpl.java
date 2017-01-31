package com.nextech.erp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.OrderrawmaterialassociationDao;
import com.nextech.erp.model.Orderrawmaterialassociation;

public class OrderrawmaterialassociationDaoImpl implements
		OrderrawmaterialassociationDao {
	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addOrderrawmaterialassociation(
			Orderrawmaterialassociation orderrawmaterialassociation) {
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(orderrawmaterialassociation);
			tx.commit();
			session.close();
		} catch (ConstraintViolationException cve) {
			System.out.println("Inside addOrderrawmaterialassociation");
			cve.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Orderrawmaterialassociation getOrderrawmaterialassociationById(
			long id) throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session
				.createCriteria(Orderrawmaterialassociation.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("id", id));
		Orderrawmaterialassociation orderrawmaterialassociation = criteria
				.list().size() > 0 ? (Orderrawmaterialassociation) criteria
				.list().get(0) : null;
		session.close();
		return orderrawmaterialassociation;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Orderrawmaterialassociation> getOrderrawmaterialassociationList()
			throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session
				.createCriteria(Orderrawmaterialassociation.class);
		criteria.add(Restrictions.eq("isactive", true));
		List<Orderrawmaterialassociation> orderrawmaterialassociationList = criteria
				.list();
		session.close();
		return orderrawmaterialassociationList;
	}

	@Override
	public boolean deleteOrderrawmaterialassociation(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Orderrawmaterialassociation.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}

	@Override
	public Orderrawmaterialassociation updateOrderrawmaterialassociation(
			Orderrawmaterialassociation orderrawmaterialassociation)
			throws Exception {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(orderrawmaterialassociation);
		session.getTransaction().commit();
		session.close();
		return orderrawmaterialassociation;
	}
}
