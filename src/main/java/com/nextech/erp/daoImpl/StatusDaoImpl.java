package com.nextech.erp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.StatusDao;
import com.nextech.erp.model.Status;

public class StatusDaoImpl implements StatusDao{

	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addStatus(Status status) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.save(status);
		tx.commit();
		session.close();
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Status getStatusById(long id) throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Status.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("id", id));
		return criteria.list().size() > 0 ? (Status) criteria.list().get(0)
				: null;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Status> getStatusist() throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Status.class);
		List<Status> statusList = criteria.list();
		session.close();
		return statusList;
	}

	@Override
	public boolean deleteStatus(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Status.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}

	@Override
	public Status updateStatus(Status status) throws Exception {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(status);
		session.getTransaction().commit();
		session.close();
		return status;
	}
}


