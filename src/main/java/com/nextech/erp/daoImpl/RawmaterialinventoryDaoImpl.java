package com.nextech.erp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.RawmaterialinventoryDao;
import com.nextech.erp.model.Rawmaterialinventory;

public class RawmaterialinventoryDaoImpl implements RawmaterialinventoryDao {
	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	@Override
	public Long addRawmaterialinventory(Rawmaterialinventory rawmaterialinventory) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		Long id = (Long) session.save(rawmaterialinventory);
		tx.commit();
		session.close();
		return id;
	}
	@SuppressWarnings("deprecation")
	@Override
	public Rawmaterialinventory getRawmaterialinventoryById(long id) throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Rawmaterialinventory.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("id", id));
		Rawmaterialinventory rawmaterialinventory = criteria.list().size() > 0 ? (Rawmaterialinventory) criteria.list()
				.get(0) : null;
		session.close();
		return rawmaterialinventory;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Rawmaterialinventory> getRawmaterialinventoryList() throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Rawmaterialinventory.class);
		criteria.add(Restrictions.eq("isactive", true));
		List<Rawmaterialinventory> rawmaterialinventoryList = criteria.list();
		session.close();
		return rawmaterialinventoryList;
	}

	@Override
	public boolean deleteRawmaterialinventory(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Rawmaterialinventory.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}

	@Override
	public Rawmaterialinventory updateRawmaterialinventory(Rawmaterialinventory rawmaterialinventory) throws Exception {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(rawmaterialinventory);
		session.getTransaction().commit();
		session.close();
		return rawmaterialinventory;
	}
}
