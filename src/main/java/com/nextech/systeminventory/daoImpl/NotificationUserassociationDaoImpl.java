package com.nextech.systeminventory.daoImpl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.nextech.systeminventory.dao.NotificationUserassociationDao;
import com.nextech.systeminventory.model.Notificationuserassociation;

@Repository

public class NotificationUserassociationDaoImpl extends
		SuperDaoImpl<Notificationuserassociation> implements
		NotificationUserassociationDao {


	@Override
	public List<Notificationuserassociation> getNotificationuserassociationByUserId(
			long userId) throws Exception {
		
		session = sessionFactory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Notificationuserassociation> criteria = builder.createQuery(Notificationuserassociation.class);
		Root<Notificationuserassociation> userRoot  = (Root<Notificationuserassociation>) criteria.from(Notificationuserassociation.class);
		criteria.select(userRoot).where(builder.equal(userRoot.get("user"), userId),builder.equal(userRoot.get("isactive"), true));
		TypedQuery<Notificationuserassociation> query = session.createQuery(criteria);
		return query.getResultList();
	}

	@Override
	public List<Notificationuserassociation> getNotificationuserassociationBynotificationId(
			long notificationId) throws Exception {
		session = sessionFactory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Notificationuserassociation> criteria = builder.createQuery(Notificationuserassociation.class);
		Root<Notificationuserassociation> userRoot  = (Root<Notificationuserassociation>) criteria.from(Notificationuserassociation.class);
		criteria.select(userRoot).where(builder.equal(userRoot.get("notification"), notificationId),builder.equal(userRoot.get("isactive"), true));
		TypedQuery<Notificationuserassociation> query = session.createQuery(criteria);
		return query.getResultList();
	}

}
