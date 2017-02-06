package com.nextech.erp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.RawmaterialDao;
import com.nextech.erp.model.Rawmaterial;

public class RawmaterialDaoImpl implements RawmaterialDao{
	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	@Override
	public Long addRawmaterial(Rawmaterial rawmaterial) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		Long id = (Long) session.save(rawmaterial);
		tx.commit();
		session.close();
		return id;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Rawmaterial getRawmaterialById(long id) throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Rawmaterial.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("id", id));
		Rawmaterial rawmaterial = criteria.list().size() > 0 ? (Rawmaterial) criteria.list()
				.get(0) : null;
		session.close();
		return rawmaterial;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Rawmaterial> getRawmaterialList() throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Rawmaterial.class);
		criteria.add(Restrictions.eq("isactive", true));
		List<Rawmaterial> rawmaterialList = criteria.list();
		session.close();
		return rawmaterialList;
	}

	@Override
	public boolean deleteRawmaterial(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Rawmaterial.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}

	@Override
	public Rawmaterial updateRawmaterial(Rawmaterial rawmaterial) throws Exception {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(rawmaterial);
		session.getTransaction().commit();
		session.close();
		return rawmaterial;
	}
}

