package com.nextech.erp.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the rawmaterialorder database table.
 * 
 */
@Entity
@NamedQuery(name="Rawmaterialorder.findAll", query="SELECT r FROM Rawmaterialorder r")
public class Rawmaterialorder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

/*	@DecimalMax(value = "100.00", message = "The actualPrice value can not be more than 100.00 ")
	@DecimalMin(value = "1.00", message = "The actualPrice value can not be less than 1.00 digit ")*/
	@Column(name="actual_price")
	private float actualPrice;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="created_date")
	private Timestamp createdDate;

	@Temporal(TemporalType.DATE)
	private Date createDate;

	@NotBlank(message="{description should not be blank}")
	@Size(min = 4, max = 255, message = "{description sholud be greater than 4 or less than 255 characters}")
	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="expected_delivery_date")
	private Date expectedDeliveryDate;

	private boolean isactive;

	@NotBlank(message="{name should not be blank}")
	@Size(min = 2, max = 255, message = "{name sholud be greater than 2 or less than 255 characters}")
	private String name;

/*	@DecimalMax(value = "100.00", message = "The otherCharges value can not be more than 100.00 ")
	@DecimalMin(value = "1.00", message = "The otherCharges value can not be less than 1.00 digit ")*/
	@Column(name="other_charges")
	private float otherCharges;

	 @Min(value = 0, message = "please enter quantity")
	/* @Max(value = 100, message = "quantity should be maximum 100")*/
	private int quantity;

	/*@DecimalMax(value = "100.00", message = "The tax value can not be more than 100.00 ")
	@DecimalMin(value = "1.00", message = "The tax value can not be less than 1.00 digit ")*/
	private float tax;

	/*@DecimalMax(value = "100.00", message = "The totalprice value can not be more than 100.00 ")
	@DecimalMin(value = "1.00", message = "The totalprice value can not be less than 1.00 digit ")*/
	private float totalprice;

	@Column(name="updated_by")
	private String updatedBy;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	//bi-directional many-to-one association to Rawmaterial
	@ManyToOne
	private Rawmaterial rawmaterial;

	//bi-directional many-to-one association to Status
	@ManyToOne
	private Status status;

	//bi-directional many-to-one association to Vendor
	@ManyToOne
	private Vendor vendor;

	//bi-directional many-to-one association to Rawmaterialorderhistory
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rawmaterialorder", cascade = CascadeType.ALL)
	private List<Rawmaterialorderassociation> rawmaterialorderassociations;
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rawmaterialorder", cascade = CascadeType.ALL)
	private List<Rawmaterialorderhistory> rawmaterialorderhistories;

	public Rawmaterialorder() {
	}

	public Rawmaterialorder(int id) {
		this.id=id;
	}


	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getActualPrice() {
		return this.actualPrice;
	}

	public void setActualPrice(float actualPrice) {
		this.actualPrice = actualPrice;
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

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getExpectedDeliveryDate() {
		return this.expectedDeliveryDate;
	}

	public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
		this.expectedDeliveryDate = expectedDeliveryDate;
	}

	public boolean getIsactive() {
		return this.isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getOtherCharges() {
		return this.otherCharges;
	}

	public void setOtherCharges(float otherCharges) {
		this.otherCharges = otherCharges;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getTax() {
		return this.tax;
	}

	public void setTax(float tax) {
		this.tax = tax;
	}

	public float getTotalprice() {
		return this.totalprice;
	}

	public void setTotalprice(float totalprice) {
		this.totalprice = totalprice;
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

	public Rawmaterial getRawmaterial() {
		return this.rawmaterial;
	}

	public void setRawmaterial(Rawmaterial rawmaterial) {
		this.rawmaterial = rawmaterial;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Vendor getVendor() {
		return this.vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public List<Rawmaterialorderhistory> getRawmaterialorderhistories() {
		return this.rawmaterialorderhistories;
	}

	public void setRawmaterialorderhistories(List<Rawmaterialorderhistory> rawmaterialorderhistories) {
		this.rawmaterialorderhistories = rawmaterialorderhistories;
	}

	public Rawmaterialorderhistory addRawmaterialorderhistory(Rawmaterialorderhistory rawmaterialorderhistory) {
		getRawmaterialorderhistories().add(rawmaterialorderhistory);
		rawmaterialorderhistory.setRawmaterialorder(this);

		return rawmaterialorderhistory;
	}

	public Rawmaterialorderhistory removeRawmaterialorderhistory(Rawmaterialorderhistory rawmaterialorderhistory) {
		getRawmaterialorderhistories().remove(rawmaterialorderhistory);
		rawmaterialorderhistory.setRawmaterialorder(null);

		return rawmaterialorderhistory;
	}

	public List<Rawmaterialorderassociation> getRawmaterialorderassociations() {
		return rawmaterialorderassociations;
	}

	public void setRawmaterialorderassociations(
			List<Rawmaterialorderassociation> rawmaterialorderassociations) {
		this.rawmaterialorderassociations = rawmaterialorderassociations;
	}

}