package com.nextech.erp.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the usertype database table.
 * 
 */
@Entity
@NamedQuery(name = "Usertype.findAll", query = "SELECT u FROM Usertype u")
public class Usertype implements Serializable {
	private static final long serialVersionUID = 1L;
   
	@NotNull
	@Id
	private long id;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;
	private boolean isactive;

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	  
	@Size(min = 5, max = 15, message = "{Please enter greater than 5 or less than 30 character description}")
	private String description;

	@Column(name = "updated_by")
	private String updatedBy;
  
	
	@Column(name = "updated_date")
	private Timestamp updatedDate;

	 @Size(min = 2, max = 15, message = "{Please enter greater than 2 or less than 15 character usertypeName}")
	@Column(name = "usertype_name")
	private String usertypeName;

	// bi-directional many-to-one association to User
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usertype", cascade = CascadeType.ALL)
	private List<User> users;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usertype", cascade = CascadeType.ALL)
	private List<Usertypepageassociation> usertypepageassociations;

	public Usertype(int id) {
		this.id=id;
	}
	
	public Usertype() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUsertypeName() {
		return this.usertypeName;
	}

	public void setUsertypeName(String usertypeName) {
		this.usertypeName = usertypeName;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setUsertype(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setUsertype(null);

		return user;
	}

	public List<Usertypepageassociation> getUsertypepageassociations() {
		return this.usertypepageassociations;
	}

	public void setUsertypepageassociations(
			List<Usertypepageassociation> usertypepageassociations) {
		this.usertypepageassociations = usertypepageassociations;
	}

	public Usertypepageassociation addUsertypepageassociation(
			Usertypepageassociation usertypepageassociation) {
		getUsertypepageassociations().add(usertypepageassociation);
		usertypepageassociation.setUsertype(this);

		return usertypepageassociation;
	}

	public Usertypepageassociation removeUsertypepageassociation(
			Usertypepageassociation usertypepageassociation) {
		getUsertypepageassociations().remove(usertypepageassociation);
		usertypepageassociation.setUsertype(null);

		return usertypepageassociation;
	}

}