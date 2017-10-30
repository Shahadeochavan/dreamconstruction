package com.nextech.systeminventory.dao;

import com.nextech.systeminventory.model.Notification;


public interface NotificationDao extends SuperDao<Notification>{

	public Notification getNotifiactionByStatus(long statusId) throws Exception;
	
	public Notification getNotificationByCode(String code) throws Exception;

}
