package com.nextech.erp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the productinventoryhistory database table.
 * 
 */
@Entity
@NamedQuery(name="Productinventoryhistory.findAll", query="SELECT p FROM Productinventoryhistory p")
public class Productinventoryhistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int afterquantity;

	private int beforequantity;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="created_date")
	private Timestamp createdDate;

	private String description;

	private byte isactive;

	@Column(name="updated_by")
	private int updatedBy;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	//bi-directional many-to-one association to Productinventory
	@ManyToOne
	@JoinColumn(name="productinventoryid")
	private Productinventory productinventory;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="statusid")
	private Status status;

	public Productinventoryhistory() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAfterquantity() {
		return this.afterquantity;
	}

	public void setAfterquantity(int afterquantity) {
		this.afterquantity = afterquantity;
	}

	public int getBeforequantity() {
		return this.beforequantity;
	}

	public void setBeforequantity(int beforequantity) {
		this.beforequantity = beforequantity;
	}

	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
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

	public byte getIsactive() {
		return this.isactive;
	}

	public void setIsactive(byte isactive) {
		this.isactive = isactive;
	}

	public int getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Productinventory getProductinventory() {
		return this.productinventory;
	}

	public void setProductinventory(Productinventory productinventory) {
		this.productinventory = productinventory;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}