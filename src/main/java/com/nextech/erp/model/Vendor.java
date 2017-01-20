package com.nextech.erp.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "vendor")
public class Vendor {

	@Id
	@Column(name = "id")
	private long id;

	@NotEmpty(message = "Please enter your company name")
	@Column(name = "company_name")
	private String company_name;

	@Email(message = "Please enter valid email address")
	@Column(name = "email")
	private String email;

	@Size(min = 2, max = 15, message = "{Please enter greater than 2 or less than 15 character firstName}")
	@Column(name = "first_name")
	private String first_name;

	@Size(min = 2, max = 15, message = "{Please enter greater than 2 or less than 15 character lastName}")
	@Column(name = "last_name")
	private String last_name;

	@NotEmpty(message = "Please enter your address")
	@Column(name = "address")
	private String address;

	@NotEmpty(message = "Please enter your valid number")
	@Size(min = 10, message = "{office number entered [${validatedValue}] is invalid. It must have at least {min} digits}")
	@Column(name = "contact_number_office")
	private String contact_number_office;

	@NotEmpty(message = "Please enter your valid number")
	@Size(min = 10,max = 10, message = "{phone number entered [${validatedValue}] is invalid. It must have at least {min} digits}")
	@Column(name = "contact_number_mobile")
	private String contact_number_mobile;

	@NotEmpty(message = "Please enter your city")
	@Column(name = "city")
	private String city;

	@NotEmpty(message = "Please enter your state")
	@Column(name = "state")
	private String state;

	@NotEmpty(message = "Please enter your postalcode")
	@Column(name = "postalcode")
	private String postalcode;

	@Column(name = "description")
	private String description;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(name = "updated_by")
	private int updatedBy;

	@Column(name = "updated_date")
	private Timestamp updatedDate;

	private boolean isactive;

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact_number_office() {
		return contact_number_office;
	}

	public void setContact_number_office(String contact_number_office) {
		this.contact_number_office = contact_number_office;
	}

	public String getContact_number_mobile() {
		return contact_number_mobile;
	}

	public void setContact_number_mobile(String contact_number_mobile) {
		this.contact_number_mobile = contact_number_mobile;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

}
