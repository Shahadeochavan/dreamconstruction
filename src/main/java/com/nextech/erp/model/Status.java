package com.nextech.erp.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="status")
public class Status {
	
	@Id
	@Column(name="id")
	private long id;
	
	@NotEmpty(message = "Please enter your name")
	@Size(min = 2, max = 15, message = "{Please enter greater than 5 or less than 30 character material name}")
	@Column(name="name")
	private String name;
	
	@Size(min = 5, max = 15, message = "{Please enter greater than 5 or less than 30 character description}")
	@Column(name="description")
	private String description;
	
	@Size(min = 5, max = 15, message = "{Please enter greater than 5 or less than 30 character type}")
	@Column(name="type")
	private String type;
	
	@Column(name = "created_By")
	private int createdBy;

	@Column(name = "created_Date")
	private Timestamp createdDate;

	@Column(name = "updated_By")
	private int updatedBy;

	@Column(name = "updated_Date")
	private Timestamp updatedDate;
	
	private boolean isactive;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
