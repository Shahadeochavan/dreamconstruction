package com.nextech.erp.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the vendor database table.
 * 
 */
@Entity
@NamedQuery(name="Vendor.findAll", query="SELECT v FROM Vendor v")
public class Vendor implements Serializable {
	private static final long serialVersionUID = 1L;
	

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;
	
	@NotBlank(message="{address should not be blank}")
	@Size(min = 2, max = 255, message = "{address sholud be greater than 2 or less than 255 characters}")
	private String address;

	@NotBlank(message="{city should not be blank}")
	@Size(min = 2, max = 255, message = "{city sholud be greater than 2 or less than 255 characters}")
	private String city;

	@NotBlank(message="{company name  should not be blank}")
	@Size(min = 2, max = 255, message = "{company name sholud be greater than 2 or less than 255 characters}")
	@Column(name="company_name")
	private String companyName;

	@NotBlank(message="{contact number  should not be blank}")
	@Size(min = 10, max = 10, message = "{contact number should be 10 digits}")
	@Column(name="contact_number_mobile")
	private String contactNumberMobile;

	@NotBlank(message="{office contact number should not be blank}")
	@Size(min = 10, max = 10, message = "{office contact number should be 10 digits}")
	@Column(name="contact_number_office")
	private String contactNumberOffice;

	@Column(name="created_by")
	private long createdBy;

	@Column(name="created_date")
	private Timestamp createdDate;

	@NotBlank(message="{description should not be blank}")
	@Size(min = 4, max = 255, message = "{description sholud be greater than 4 or less than 255 characters}")
	private String description;

	//@Email(message="{email should be enter valid")
	 @NotEmpty
	 @Email
	private String email;

	 @NotEmpty
	//@NotBlank(message="{first Name should not be blank}")
	@Size(min = 2, max = 255)
	@Column(name="first_name")
	private String firstName;

	private boolean isactive;

	@NotBlank(message="{last Name should not be blank}")
	@Size(min = 2, max = 255, message = "{last Name sholud be greater than 2 or less than 255 characters}")
	@Column(name="last_name")
	private String lastName;

	@NotBlank(message="{postalcode should not be blank}")
	private String postalcode;

	@NotBlank(message="{state should not be blank}")
	@Size(min = 2, max = 255, message = "{state sholud be greater than 2 or less than 255 characters}")
	private String state;

	@Column(name="updated_by")
	private long updatedBy;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	//bi-directional many-to-one association to Rawmaterialorder
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "vendor", cascade = CascadeType.ALL)
	private List<Rawmaterialorder> rawmaterialorders;

	//bi-directional many-to-one association to Rawmaterialvendorassociation
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "vendor", cascade = CascadeType.ALL)
	private List<Rawmaterialvendorassociation> rawmaterialvendorassociations;

	public Vendor() {
	}
	public Vendor(int id) {
		this.id=id;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContactNumberMobile() {
		return this.contactNumberMobile;
	}

	public void setContactNumberMobile(String contactNumberMobile) {
		this.contactNumberMobile = contactNumberMobile;
	}

	public String getContactNumberOffice() {
		return this.contactNumberOffice;
	}

	public void setContactNumberOffice(String contactNumberOffice) {
		this.contactNumberOffice = contactNumberOffice;
	}

	public long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(long createdBy) {
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public boolean getIsactive() {
		return this.isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPostalcode() {
		return this.postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<Rawmaterialorder> getRawmaterialorders() {
		return this.rawmaterialorders;
	}

	public void setRawmaterialorders(List<Rawmaterialorder> rawmaterialorders) {
		this.rawmaterialorders = rawmaterialorders;
	}

	public Rawmaterialorder addRawmaterialorder(Rawmaterialorder rawmaterialorder) {
		getRawmaterialorders().add(rawmaterialorder);
		rawmaterialorder.setVendor(this);

		return rawmaterialorder;
	}

	public Rawmaterialorder removeRawmaterialorder(Rawmaterialorder rawmaterialorder) {
		getRawmaterialorders().remove(rawmaterialorder);
		rawmaterialorder.setVendor(null);

		return rawmaterialorder;
	}

	public List<Rawmaterialvendorassociation> getRawmaterialvendorassociations() {
		return this.rawmaterialvendorassociations;
	}

	public void setRawmaterialvendorassociations(List<Rawmaterialvendorassociation> rawmaterialvendorassociations) {
		this.rawmaterialvendorassociations = rawmaterialvendorassociations;
	}

	public Rawmaterialvendorassociation addRawmaterialvendorassociation(Rawmaterialvendorassociation rawmaterialvendorassociation) {
		getRawmaterialvendorassociations().add(rawmaterialvendorassociation);
		rawmaterialvendorassociation.setVendor(this);

		return rawmaterialvendorassociation;
	}

	public Rawmaterialvendorassociation removeRawmaterialvendorassociation(Rawmaterialvendorassociation rawmaterialvendorassociation) {
		getRawmaterialvendorassociations().remove(rawmaterialvendorassociation);
		rawmaterialvendorassociation.setVendor(null);

		return rawmaterialvendorassociation;
	}

}