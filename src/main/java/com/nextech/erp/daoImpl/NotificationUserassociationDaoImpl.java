package com.nextech.erp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.nextech.erp.dao.NotificationUserassociationDao;
import com.nextech.erp.model.Dispatch;
import com.nextech.erp.model.Notification;
import com.nextech.erp.model.Notificationuserassociation;

public class NotificationUserassociationDaoImpl extends
		SuperDaoImpl<Notificationuserassociation> implements
		NotificationUserassociationDao {

	@Override
	public Notificationuserassociation getNotifiactionByUserId(long userId)
			throws Exception {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session
				.createCriteria(Notificationuserassociation.class);
		criteria.add(Restrictions.eq("user.id", userId));
		criteria.add(Restrictions.eq("isactive", true));
		Notificationuserassociation notificationuserassociation = criteria
				.list().size() > 0 ? (Notificationuserassociation) criteria
				.list().get(0) : null;
		return notificationuserassociation;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notificationuserassociation> getNotificationuserassociationByUserId(
			long userId) throws Exception {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session
				.createCriteria(Notificationuserassociation.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("user.id", userId));
		return (criteria.list().size() > 0 ? (List<Notificationuserassociation>) criteria
				.list() : null);
	}

}
