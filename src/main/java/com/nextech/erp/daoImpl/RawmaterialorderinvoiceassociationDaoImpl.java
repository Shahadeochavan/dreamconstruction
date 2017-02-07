package com.nextech.erp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.RawmaterialorderinvoiceassociationDao;
import com.nextech.erp.model.Rawmaterialorderinvoiceassociation;

public class RawmaterialorderinvoiceassociationDaoImpl implements RawmaterialorderinvoiceassociationDao{
	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	@Override
	public Long addRawmaterialorderinvoiceassociation(Rawmaterialorderinvoiceassociation Rawmaterialorderinvoiceassociation) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		Long id = (Long) session.save(Rawmaterialorderinvoiceassociation);
		tx.commit();
		session.close();
		return id;
	}
	@SuppressWarnings("deprecation")
	@Override
	public Rawmaterialorderinvoiceassociation getRawmaterialorderinvoiceassociationById(long id) throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Rawmaterialorderinvoiceassociation.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("id", id));
		Rawmaterialorderinvoiceassociation Rawmaterialorderinvoiceassociation =criteria.list().size() > 0 ? (Rawmaterialorderinvoiceassociation) criteria.list().get(0)
				: null;
		session.close();
		return Rawmaterialorderinvoiceassociation;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Rawmaterialorderinvoiceassociation> getRawmaterialorderinvoiceassociationList() throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Rawmaterialorderinvoiceassociation.class);
		criteria.add(Restrictions.eq("isactive", true));
		List<Rawmaterialorderinvoiceassociation> RawmaterialorderinvoiceassociationList = criteria.list();
		session.close();
		return RawmaterialorderinvoiceassociationList;
	}

	@Override
	public boolean deleteRawmaterialorderinvoiceassociation(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Rawmaterialorderinvoiceassociation.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}

	@Override
	public Rawmaterialorderinvoiceassociation updateRawmaterialorderinvoiceassociation(Rawmaterialorderinvoiceassociation Rawmaterialorderinvoiceassociation) throws Exception {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(Rawmaterialorderinvoiceassociation);
		session.getTransaction().commit();
		session.close();
		return Rawmaterialorderinvoiceassociation;
	}
}

