package com.nextech.erp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.RawmaterialorderDao;
import com.nextech.erp.model.Rawmaterialorder;

public class RawmaterialorderDaoImpl implements RawmaterialorderDao{
	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addRawmaterialorder(Rawmaterialorder rawmaterialorder) {
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(rawmaterialorder);
			tx.commit();
			session.close();
		} catch (ConstraintViolationException cve) {
			System.out.println("Inside addRawmaterialorder");
			cve.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Rawmaterialorder getRawmaterialorderById(long id) throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Rawmaterialorder.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("id", id));
		Rawmaterialorder rawmaterialorder = criteria.list().size() > 0 ? (Rawmaterialorder) criteria.list()
				.get(0) : null;
		session.close();
		return rawmaterialorder;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Rawmaterialorder> getRawmaterialorderList() throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Rawmaterialorder.class);
		criteria.add(Restrictions.eq("isactive", true));
		List<Rawmaterialorder> rawmaterialorderList = criteria.list();
		session.close();
		return rawmaterialorderList;
	}

	@Override
	public boolean deleteRawmaterialorder(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Rawmaterialorder.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}

	@Override
	public Rawmaterialorder updateRawmaterialorder(Rawmaterialorder rawmaterialorder) throws Exception {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(rawmaterialorder);
		session.getTransaction().commit();
		session.close();
		return rawmaterialorder;
	}
}
