package com.nextech.dreamConstruction.dto;

import java.util.List;


public class NotificationDTO extends AbstractDTO{
	
	private String beanClass;
	private String name;
	private String subject;
	private String template;
	private String type;
	private StatusDTO status1;
	private StatusDTO status2;
	private String code;
	
	public NotificationDTO(){
		
	}
       public NotificationDTO(int id){
		this.setId(id);
	}
	
	private List<NotificationUserAssociatinsDTO> notificationUserAssociatinsDTOs;
	public String getBeanClass() {
		return beanClass;
	}
	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public StatusDTO getStatus1() {
		return status1;
	}
	public void setStatus1(StatusDTO status1) {
		this.status1 = status1;
	}
	public StatusDTO getStatus2() {
		return status2;
	}
	public void setStatus2(StatusDTO status2) {
		this.status2 = status2;
	}
	public List<NotificationUserAssociatinsDTO> getNotificationUserAssociatinsDTOs() {
		return notificationUserAssociatinsDTOs;
	}
	public void setNotificationUserAssociatinsDTOs(
			List<NotificationUserAssociatinsDTO> notificationUserAssociatinsDTOs) {
		this.notificationUserAssociatinsDTOs = notificationUserAssociatinsDTOs;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	

}
