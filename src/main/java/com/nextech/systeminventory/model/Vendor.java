package com.nextech.systeminventory.model;

import java.io.Serializable;

import javax.persistence.*;

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

	private String address;

	private String city;

	private String commisionerate;

	@Column(name="company_name")
	private String companyName;

	@Column(name="contact_number_mobile")
	private String contactNumberMobile;

	@Column(name="contact_number_office")
	private String contactNumberOffice;

	@Column(name="created_by")
	private long createdBy;

	@Column(name="created_date")
	private Timestamp createdDate;

	private String cst;

	@Column(name="customer_ecc_number")
	private String customerEccNumber;

	private String description;

	private String divison;

	private String email;

	@Column(name="first_name")
	private String firstName;

	private boolean isactive;

	@Column(name="last_name")
	private String lastName;

	private String postalcode;


	private String renge; 
	
	private String state;

	@Column(name="updated_by")
	private long updatedBy;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	@Column(name="vat_no")
	private String vatNo;
	
	//bi-directional many-to-one association to PrVndrAssn
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "vendor", cascade = CascadeType.ALL)
	private List<PrVndrAssn> prVndrAssns;

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

	public String getCommisionerate() {
		return this.commisionerate;
	}

	public void setCommisionerate(String commisionerate) {
		this.commisionerate = commisionerate;
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

	public String getCst() {
		return this.cst;
	}

	public void setCst(String cst) {
		this.cst = cst;
	}

	public String getCustomerEccNumber() {
		return this.customerEccNumber;
	}

	public void setCustomerEccNumber(String customerEccNumber) {
		this.customerEccNumber = customerEccNumber;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDivison() {
		return this.divison;
	}

	public void setDivison(String divison) {
		this.divison = divison;
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
	
	public String getRenge() {
		return renge;
	}
	public void setRenge(String renge) {
		this.renge = renge;
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

	public String getVatNo() {
		return this.vatNo;
	}

	public void setVatNo(String vatNo) {
		this.vatNo = vatNo;
	}
	public List<PrVndrAssn> getPrVndrAssns() {
		return this.prVndrAssns;
	}

	public void setPrVndrAssns(List<PrVndrAssn> prVndrAssns) {
		this.prVndrAssns = prVndrAssns;
	}

	public PrVndrAssn addPrVndrAssn(PrVndrAssn prVndrAssn) {
		getPrVndrAssns().add(prVndrAssn);
		prVndrAssn.setVendor(this);

		return prVndrAssn;
	}
}