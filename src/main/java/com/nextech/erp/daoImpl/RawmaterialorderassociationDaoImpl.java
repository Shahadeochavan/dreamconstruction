package com.nextech.erp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.RawmaterialorderassociationDao;
import com.nextech.erp.model.Rawmaterialorderassociation;

public class RawmaterialorderassociationDaoImpl implements
		RawmaterialorderassociationDao {
	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addRawmaterialorderassociation(
			Rawmaterialorderassociation Rawmaterialorderassociation) {
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(Rawmaterialorderassociation);
			tx.commit();
			session.close();
		} catch (ConstraintViolationException cve) {
			System.out.println("Inside adRawmaterialorderassociation");
			cve.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Rawmaterialorderassociation getRawmaterialorderassociationById(
			long id) throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session
				.createCriteria(Rawmaterialorderassociation.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("id", id));
		Rawmaterialorderassociation Rawmaterialorderassociation = criteria
				.list().size() > 0 ? (Rawmaterialorderassociation) criteria
				.list().get(0) : null;
		session.close();
		return Rawmaterialorderassociation;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Rawmaterialorderassociation> getRawmaterialorderassociationList()
			throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session
				.createCriteria(Rawmaterialorderassociation.class);
		criteria.add(Restrictions.eq("isactive", true));
		List<Rawmaterialorderassociation> RawmaterialorderassociationList = criteria
				.list();
		session.close();
		return RawmaterialorderassociationList;
	}

	@Override
	public boolean deleteRawmaterialorderassociation(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Rawmaterialorderassociation.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}

	@Override
	public Rawmaterialorderassociation updateRawmaterialorderassociation(
			Rawmaterialorderassociation Rawmaterialorderassociation)
			throws Exception {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(Rawmaterialorderassociation);
		session.getTransaction().commit();
		session.close();
		return Rawmaterialorderassociation;
	}
}
