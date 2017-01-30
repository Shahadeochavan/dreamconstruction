package com.nextech.erp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.UsertypepageassociationDao;
import com.nextech.erp.model.Usertypepageassociation;

public class UsertypepageassociationDaoImpl implements UsertypepageassociationDao {
	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addPageAss(Usertypepageassociation usertypepageassociation) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.save(usertypepageassociation);
		tx.commit();
		session.close();
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Usertypepageassociation getPageAssById(long id) throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Usertypepageassociation.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("id", id));
		Usertypepageassociation usertypepageassociation =criteria.list().size() > 0 ? (Usertypepageassociation) criteria.list().get(0)
				: null;
		session.close();
		return usertypepageassociation;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Usertypepageassociation> getPageAssList() throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Usertypepageassociation.class);
		List<Usertypepageassociation> usertypepageassociationList = criteria.list();
		session.close();
		return usertypepageassociationList;
	}

	@Override
	public boolean deletePageAss(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Usertypepageassociation.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}

	@Override
	public Usertypepageassociation updatePageAss(Usertypepageassociation usertypepageassociation) throws Exception {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(usertypepageassociation);
		session.getTransaction().commit();
		session.close();
		return usertypepageassociation;
	}
}


