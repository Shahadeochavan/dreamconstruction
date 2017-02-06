package com.nextech.erp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.RawmaterialorderhistoryDao;
import com.nextech.erp.model.Rawmaterialorderhistory;

public class RawmaterialorderhistoryDaoImpl implements RawmaterialorderhistoryDao{
	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	@Override
	public Long addRawmaterialorderhistory(Rawmaterialorderhistory rawmaterialorderhistory) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		Long id = (Long) session.save(rawmaterialorderhistory);
		tx.commit();
		session.close();
		return id;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Rawmaterialorderhistory getRawmaterialorderhistoryById(long id) throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Rawmaterialorderhistory.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("id", id));
		Rawmaterialorderhistory rawmaterialorderhistory = criteria.list().size() > 0 ? (Rawmaterialorderhistory) criteria.list()
				.get(0) : null;
		session.close();
		return rawmaterialorderhistory;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Rawmaterialorderhistory> getRawmaterialorderhistoryList() throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Rawmaterialorderhistory.class);
		criteria.add(Restrictions.eq("isactive", true));
		List<Rawmaterialorderhistory> rawmaterialorderhistoryList = criteria.list();
		session.close();
		return rawmaterialorderhistoryList;
	}

	@Override
	public boolean deleteRawmaterialorderhistory(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Rawmaterialorderhistory.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}

	@Override
	public Rawmaterialorderhistory updateRawmaterialorderhistory(Rawmaterialorderhistory rawmaterialorderhistory) throws Exception {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(rawmaterialorderhistory);
		session.getTransaction().commit();
		session.close();
		return rawmaterialorderhistory;
	}
}

