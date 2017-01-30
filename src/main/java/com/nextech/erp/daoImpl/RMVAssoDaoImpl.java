package com.nextech.erp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.RMVAssoDao;
import com.nextech.erp.model.Rawmaterialvendorassociation;

public class RMVAssoDaoImpl implements RMVAssoDao{

	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addRawmaterialvendorassociation(Rawmaterialvendorassociation rawmaterialvendorassociation) {
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(rawmaterialvendorassociation);
			tx.commit();
			session.close();
		} catch (ConstraintViolationException cve) {
			System.out.println("Inside addRawmaterialvendorassociation");
			cve.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Rawmaterialvendorassociation getRawmaterialvendorassociationById(long id) throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Rawmaterialvendorassociation.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("id", id));
		Rawmaterialvendorassociation rawmaterialvendorassociation = criteria.list().size() > 0 ? (Rawmaterialvendorassociation) criteria.list()
				.get(0) : null;
		session.close();
		return rawmaterialvendorassociation;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Rawmaterialvendorassociation> getRawmaterialvendorassociationList() throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Rawmaterialvendorassociation.class);
		criteria.add(Restrictions.eq("isactive", true));
		List<Rawmaterialvendorassociation> rawmaterialvendorassociationList = criteria.list();
		session.close();
		return rawmaterialvendorassociationList;
	}

	@Override
	public boolean deleteRawmaterialvendorassociation(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Rawmaterialvendorassociation.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}

	@Override
	public Rawmaterialvendorassociation updateRawmaterialvendorassociation(Rawmaterialvendorassociation rawmaterialvendorassociation) throws Exception {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(rawmaterialvendorassociation);
		session.getTransaction().commit();
		session.close();
		return rawmaterialvendorassociation;
	}
}
