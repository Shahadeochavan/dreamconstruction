package com.nextech.erp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.StatustransitionDao;
import com.nextech.erp.model.Statustransition;

public class StatustransitionDaoImpl implements StatustransitionDao {
	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	@Override
	public Long addStatustransition(Statustransition statustransition) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		Long id = (Long) session.save(statustransition);
		tx.commit();
		session.close();
		return id;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Statustransition getStatustransitionById(long id) throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Statustransition.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("id", id));
		Statustransition statustransition =criteria.list().size() > 0 ? (Statustransition) criteria.list()
				.get(0) : null;
				session.close();
				return statustransition;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Statustransition> getStatustransitionList() throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Statustransition.class);
		criteria.add(Restrictions.eq("isactive", true));
		List<Statustransition> StatustransitionList = criteria.list();
		session.close();
		return StatustransitionList;
	}

	@Override
	public boolean deleteStatustransition(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Statustransition.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}

	@Override
	public Statustransition updateStatustransition(
			Statustransition statustransition) throws Exception {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(statustransition);
		session.getTransaction().commit();
		session.close();
		return statustransition;
	}
	
	@Override
	public Statustransition getStatustransitionByEmail(String email) throws Exception{
		session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Statustransition.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("isNotificationEmail", email));
		Statustransition statustransition= criteria.list().size() > 0 ? (Statustransition) criteria.list()
				.get(0) : null;
				session.close();
				return statustransition;
	}
}
