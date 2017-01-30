package com.nextech.erp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.UnitDao;
import com.nextech.erp.model.Unit;

public class UnitDaoImpl implements UnitDao{
	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addUnit(Unit unit) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.save(unit);
		tx.commit();
		session.close();
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Unit getUnitById(long id) throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Unit.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("id", id));
		Unit unit= criteria.list().size() > 0 ? (Unit) criteria.list().get(0)
				: null;
		session.close();
		return unit;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Unit> getUnitist() throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Unit.class);
		criteria.add(Restrictions.eq("isactive", true));
		List<Unit> unitList = criteria.list();
		session.close();
		return unitList;
	}

	@Override
	public boolean deleteUnit(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Unit.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}

	@Override
	public Unit updateUnit(Unit unit) throws Exception {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(unit);
		session.getTransaction().commit();
		session.close();
		return unit;
	}
}


