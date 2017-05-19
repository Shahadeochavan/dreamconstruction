package com.nextech.erp.dao;

import com.nextech.erp.model.Notificationuserassociation;

public interface NotificationUserassociationDao extends SuperDao<Notificationuserassociation> {

	public Notificationuserassociation getNotifiactionByUserId(long userId) throws Exception;

}
