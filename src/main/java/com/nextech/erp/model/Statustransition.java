package com.nextech.erp.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "statustransition")
public class Statustransition {

	@NotNull
	@Id
	@Column(name = "id")
	private long id;

	@NotEmpty(message = "Please enter your name")
	@Size(min = 2, max = 15, message = "{Please enter greater than 5 or less than 30 character material name}")
	@Column(name = "name")
	private String name;

	@Size(min = 5, max = 15, message = "{Please enter greater than 5 or less than 30 character description}")
	@Column(name = "description")
	private String description;

	@NotEmpty(message = "Please enter your from status")
	@Size(min = 2, max = 15, message = "{Please enter greater than 5 or less than 30 character status}")
	@Column(name = "from_status")
	private String from_status;

	@NotEmpty(message = "Please enter your to status")
	@Size(min = 2, max = 15, message = "{Please enter greater than 5 or less than 30 character status}")
	@Column(name = "to_status")
	private String to_status;

	@NotEmpty(message = "Please enter your email address")
	@Email(message = "Please enter valid email address")
	@Column(name = "isNotificationEmail")
	private String isNotificationEmail;

	@Size(min = 10,max = 10, message = "{phone number entered valid number and 10 minimum digits}")
	@Column(name = "isNotificationSMS")
	private String isNotificationSMS;

	@Column(name = "created_By")
	private int createdBy;

	@Column(name = "created_Date")
	private Timestamp createdDate;

	@Column(name = "updated_By")
	private int updatedBy;

	@Column(name = "updated_Date")
	private Timestamp updatedDate;

	private boolean isactive;

	public String getTo_status() {
		return to_status;
	}

	public void setTo_status(String to_status) {
		this.to_status = to_status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFrom_status() {
		return from_status;
	}

	public void setFrom_status(String from_status) {
		this.from_status = from_status;
	}

	public String getIsNotificationEmail() {
		return isNotificationEmail;
	}

	public void setIsNotificationEmail(String isNotificationEmail) {
		this.isNotificationEmail = isNotificationEmail;
	}

	public String getIsNotificationSMS() {
		return isNotificationSMS;
	}

	public void setIsNotificationSMS(String isNotificationSMS) {
		this.isNotificationSMS = isNotificationSMS;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

}
