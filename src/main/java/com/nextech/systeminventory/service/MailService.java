package com.nextech.systeminventory.service;

import com.nextech.systeminventory.dto.NotificationDTO;
import com.nextech.systeminventory.model.Mail;


public interface MailService {

	public void sendEmail(Mail mail, NotificationDTO notification);

	public void sendEmailWithoutPdF(Mail mail, NotificationDTO notification);

	public Mail setMailCCBCCAndTO(NotificationDTO notificationDTO) throws Exception;

	public String getSubject(NotificationDTO notificationDTO);

	public String getContent(NotificationDTO notificationDTO);
}