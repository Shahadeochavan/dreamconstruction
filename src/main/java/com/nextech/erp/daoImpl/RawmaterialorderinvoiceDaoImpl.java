package com.nextech.erp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.RawmaterialorderinvoiceDao;
import com.nextech.erp.model.Rawmaterialorder;
import com.nextech.erp.model.Rawmaterialorderinvoice;

public class RawmaterialorderinvoiceDaoImpl implements
		RawmaterialorderinvoiceDao {
	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	@Override
	public Long addRawmaterialorderinvoice(
			Rawmaterialorderinvoice Rawmaterialorderinvoice) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		Long id = (Long) session.save(Rawmaterialorderinvoice);
		tx.commit();
		session.close();
		return id;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Rawmaterialorderinvoice getRawmaterialorderinvoiceById(long id)
			throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session
				.createCriteria(Rawmaterialorderinvoice.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("id", id));
		Rawmaterialorderinvoice Rawmaterialorderinvoice = criteria.list()
				.size() > 0 ? (Rawmaterialorderinvoice) criteria.list().get(0)
				: null;
		session.close();
		return Rawmaterialorderinvoice;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Rawmaterialorderinvoice> getRawmaterialorderinvoiceList()
			throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session
				.createCriteria(Rawmaterialorderinvoice.class);
		criteria.add(Restrictions.eq("isactive", true));
		List<Rawmaterialorderinvoice> rawmaterialorderinvoiceList = criteria
				.list();
		session.close();
		return rawmaterialorderinvoiceList;
	}

	@Override
	public boolean deleteRawmaterialorderinvoice(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Rawmaterialorderinvoice.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}

	@Override
	public Rawmaterialorderinvoice updateRawmaterialorderinvoice(
			Rawmaterialorderinvoice Rawmaterialorderinvoice) throws Exception {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(Rawmaterialorderinvoice);
		session.getTransaction().commit();
		session.close();
		return Rawmaterialorderinvoice;
	}

}
