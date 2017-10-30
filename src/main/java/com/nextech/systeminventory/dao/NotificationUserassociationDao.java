package com.nextech.systeminventory.dao;

import java.util.List;

import com.nextech.systeminventory.model.Notificationuserassociation;


public interface NotificationUserassociationDao extends SuperDao<Notificationuserassociation> {

	List<Notificationuserassociation> getNotificationuserassociationByUserId(long userId) throws Exception;
	
	List<Notificationuserassociation> getNotificationuserassociationBynotificationId(long notificationId) throws Exception;

}
