package com.nextech.erp.service;

import com.nextech.erp.model.Notificationuserassociation;

public interface NotificationUserAssociationService extends CRUDService<Notificationuserassociation>{

	public Notificationuserassociation getNotifiactionByUserId(long userId) throws Exception;

}
