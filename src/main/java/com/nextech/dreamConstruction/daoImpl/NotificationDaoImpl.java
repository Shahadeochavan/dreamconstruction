package com.nextech.dreamConstruction.daoImpl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nextech.dreamConstruction.dao.NotificationDao;
import com.nextech.dreamConstruction.model.Notification;


@Repository

public class NotificationDaoImpl extends SuperDaoImpl<Notification> implements NotificationDao{
	
	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	@Override
	public Notification getNotifiactionByStatus(long statusId) throws Exception {
		
		session = sessionFactory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Notification> criteria = builder.createQuery(Notification.class);
		Root<Notification> userRoot = (Root<Notification>) criteria.from(Notification.class);
		criteria.select(userRoot).where(builder.equal(userRoot.get("status1"), statusId),builder.equal(userRoot.get("isactive"), true));
		TypedQuery<Notification> query = session.createQuery(criteria);
		  List<Notification> list = query.getResultList();
		  if (list.isEmpty()) {
		        return null;
		    }
		    return list.get(0);
	}

	@Override
	public Notification getNotificationByCode(String code) throws Exception {
		
		session = sessionFactory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Notification> criteria = builder.createQuery(Notification.class);
		Root<Notification> userRoot = (Root<Notification>) criteria.from(Notification.class);
		criteria.select(userRoot).where(builder.equal(userRoot.get("code"), code),builder.equal(userRoot.get("isactive"), true));
		TypedQuery<Notification> query = session.createQuery(criteria);
		  List<Notification> list = query.getResultList();
		  if (list.isEmpty()) {
		        return null;
		    }
		    return list.get(0);
	}
}
