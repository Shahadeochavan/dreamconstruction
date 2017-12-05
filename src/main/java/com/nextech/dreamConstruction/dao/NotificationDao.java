package com.nextech.dreamConstruction.dao;

import com.nextech.dreamConstruction.model.Notification;


public interface NotificationDao extends SuperDao<Notification>{

	public Notification getNotifiactionByStatus(long statusId) throws Exception;
	
	public Notification getNotificationByCode(String code) throws Exception;

}
