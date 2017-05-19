package com.nextech.erp.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.NotificationUserassociationDao;
import com.nextech.erp.model.Notificationuserassociation;
import com.nextech.erp.service.NotificationUserAssociationService;

public class NotificationUserAssociationServiceImpl extends CRUDServiceImpl<Notificationuserassociation> implements NotificationUserAssociationService {

	@Autowired
	NotificationUserassociationDao notificationUserassociationDao;

	@Override
	public Notificationuserassociation getNotifiactionByUserId(long userId)
			throws Exception {
		// TODO Auto-generated method stub
		return notificationUserassociationDao.getNotifiactionByUserId(userId);
	}

}
