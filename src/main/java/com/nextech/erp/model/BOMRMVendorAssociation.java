package com.nextech.erp.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;


@Entity
@NamedQuery(name="BOMRMVendorAssociation.findAll", query="SELECT b FROM BOMRMVendorAssociation b")
public class BOMRMVendorAssociation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;


	@Column(name="created_by")
	private String createdBy;

	@Column(name="created_date")
	private Timestamp createdDate;

	
	private boolean isactive;

	private long pricePerUnit;
	
	private long cost;

	private long quantity;

	@Column(name="updated_by")
	private String updatedBy;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="bomid")
	private Bom bom;

	//bi-directional many-to-one association to Rawmaterial
	@ManyToOne
	@JoinColumn(name="rawmaterialid")
	private Rawmaterial rawmaterial;

	//bi-directional many-to-one association to Vendor
	@ManyToOne
	@JoinColumn(name="vendorid")
	private Vendor vendor;

	public BOMRMVendorAssociation() {
	}
	public BOMRMVendorAssociation(int id) {
		this.id=id;
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

	public boolean getIsactive() {
		return this.isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public long getPricePerUnit() {
		return this.pricePerUnit;
	}

	public void setPricePerUnit(long pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public long getQuantity() {
		return this.quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
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

	public Bom getBom() {
		return bom;
	}
	public void setBom(Bom bom) {
		this.bom = bom;
	}
	public Rawmaterial getRawmaterial() {
		return this.rawmaterial;
	}

	public void setRawmaterial(Rawmaterial rawmaterial) {
		this.rawmaterial = rawmaterial;
	}

	public Vendor getVendor() {
		return this.vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	public long getCost() {
		return cost;
	}
	public void setCost(long cost) {
		this.cost = cost;
	}

}