package com.nextech.systeminventory.service;

import java.util.List;

import com.nextech.systeminventory.dto.NotificationDTO;
import com.nextech.systeminventory.model.Notification;


public interface NotificationService extends CRUDService<Notification>{

	public NotificationDTO getNotifiactionByStatus(long statusId) throws Exception;
	
	public List<NotificationDTO> getNofificationList(List<NotificationDTO> notificationDTOs) throws Exception;
	
	public NotificationDTO getNotificationDTOById(long id) throws Exception;
	
	public NotificationDTO deleteNofificationById(long id) throws Exception;
	
	public NotificationDTO getNotificationByCode(String code)throws Exception;

}
