package com.nextech.erp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.RawmaterialinventoryhistoryDao;
import com.nextech.erp.model.Rawmaterialinventoryhistory;

public class RawmaterialinventoryhistoryDaoImpl implements RawmaterialinventoryhistoryDao{

	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addRawmaterialinventoryhistory(Rawmaterialinventoryhistory rawmaterialinventoryhistory) {
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(rawmaterialinventoryhistory);
			tx.commit();
			session.close();
		} catch (ConstraintViolationException cve) {
			System.out.println("Inside addRawmaterialinventoryhistory");
			cve.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Rawmaterialinventoryhistory getRawmaterialinventoryhistoryById(long id) throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Rawmaterialinventoryhistory.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("id", id));
		Rawmaterialinventoryhistory rawmaterialinventoryhistory = criteria.list().size() > 0 ? (Rawmaterialinventoryhistory) criteria.list()
				.get(0) : null;
		session.close();
		return rawmaterialinventoryhistory;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Rawmaterialinventoryhistory> getRawmaterialinventoryhistoryList() throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Rawmaterialinventoryhistory.class);
		criteria.add(Restrictions.eq("isactive", true));
		List<Rawmaterialinventoryhistory> rawmaterialinventoryhistoryList = criteria.list();
		session.close();
		return rawmaterialinventoryhistoryList;
	}

	@Override
	public boolean deleteRawmaterialinventoryhistory(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Rawmaterialinventoryhistory.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}

	@Override
	public Rawmaterialinventoryhistory updateRawmaterialinventoryhistory(Rawmaterialinventoryhistory rawmaterialinventoryhistory) throws Exception {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(rawmaterialinventoryhistory);
		session.getTransaction().commit();
		session.close();
		return rawmaterialinventoryhistory;
	}
}
