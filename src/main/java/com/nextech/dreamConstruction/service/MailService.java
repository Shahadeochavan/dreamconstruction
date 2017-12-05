package com.nextech.dreamConstruction.service;

import com.nextech.dreamConstruction.dto.NotificationDTO;
import com.nextech.dreamConstruction.model.Mail;


public interface MailService {

	public void sendEmail(Mail mail, NotificationDTO notification);

	public void sendEmailWithoutPdF(Mail mail, NotificationDTO notification);

	public Mail setMailCCBCCAndTO(NotificationDTO notificationDTO) throws Exception;

	public String getSubject(NotificationDTO notificationDTO);

	public String getContent(NotificationDTO notificationDTO);
}