package com.nextech.erp.daoImpl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.nextech.erp.dao.NotificationUserassociationDao;
import com.nextech.erp.model.Notification;
import com.nextech.erp.model.Notificationuserassociation;

public class NotificationUserassociationDaoImpl extends SuperDaoImpl<Notificationuserassociation> implements NotificationUserassociationDao{

	@Override
	public Notificationuserassociation getNotifiactionByUserId(long userId)
			throws Exception {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Notificationuserassociation.class);
		criteria.add(Restrictions.eq("user.id",userId));
		criteria.add(Restrictions.eq("isactive", true));
		Notificationuserassociation notificationuserassociation = criteria.list().size() > 0 ? (Notificationuserassociation) criteria.list().get(0) : null;
		return notificationuserassociation;
	}

}
