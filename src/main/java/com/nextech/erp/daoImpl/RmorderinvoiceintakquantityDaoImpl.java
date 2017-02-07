package com.nextech.erp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.RmorderinvoiceintakquantityDao;
import com.nextech.erp.model.Rmorderinvoiceintakquantity;

public class RmorderinvoiceintakquantityDaoImpl implements RmorderinvoiceintakquantityDao{
	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	@Override
	public Long addRmorderinvoiceintakquantity(Rmorderinvoiceintakquantity rmorderinvoiceintakquantity) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		Long id = (Long) session.save(rmorderinvoiceintakquantity);
		tx.commit();
		session.close();
		return id;
	}
	@SuppressWarnings("deprecation")
	@Override
	public Rmorderinvoiceintakquantity getRmorderinvoiceintakquantityById(long id) throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Rmorderinvoiceintakquantity.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("id", id));
		Rmorderinvoiceintakquantity rmorderinvoiceintakquantity =criteria.list().size() > 0 ? (Rmorderinvoiceintakquantity) criteria.list().get(0)
				: null;
		session.close();
		return rmorderinvoiceintakquantity;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Rmorderinvoiceintakquantity> getRmorderinvoiceintakquantityList() throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Rmorderinvoiceintakquantity.class);
		criteria.add(Restrictions.eq("isactive", true));
		List<Rmorderinvoiceintakquantity> rmorderinvoiceintakquantityList = criteria.list();
		session.close();
		return rmorderinvoiceintakquantityList;
	}

	@Override
	public boolean deleteRmorderinvoiceintakquantity(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Rmorderinvoiceintakquantity.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}

	@Override
	public Rmorderinvoiceintakquantity updateRmorderinvoiceintakquantity(Rmorderinvoiceintakquantity rmorderinvoiceintakquantity) throws Exception {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(rmorderinvoiceintakquantity);
		session.getTransaction().commit();
		session.close();
		return rmorderinvoiceintakquantity;
	}
}

