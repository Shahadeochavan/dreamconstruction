package com.nextech.erp.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the qualitycheckrawmaterial database table.
 * 
 */
@Entity
@NamedQuery(name="Qualitycheckrawmaterial.findAll", query="SELECT q FROM Qualitycheckrawmaterial q")
public class Qualitycheckrawmaterial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="created_date")
	private Timestamp createdDate;

	private int goodQuantity;

	private int intakeQuantity;

	private boolean isactive;

	private byte isReturnInvoiceInitated;

	private String remark;

	@Column(name="updated_by")
	private int updatedBy;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	//bi-directional many-to-one association to Rawmaterialorderinvoice
	@ManyToOne
	@JoinColumn(name="rmorderinvoiceid")
	private Rawmaterialorderinvoice rawmaterialorderinvoice;

	//bi-directional many-to-one association to Rawmaterial
	@ManyToOne
	@JoinColumn(name="rawmaterialid")
	private Rawmaterial rawmaterial;

	public Qualitycheckrawmaterial() {
	}
	public Qualitycheckrawmaterial(int id) {
		this.id=id;
	}


	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
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

	public int getGoodQuantity() {
		return this.goodQuantity;
	}

	public void setGoodQuantity(int goodQuantity) {
		this.goodQuantity = goodQuantity;
	}

	public int getIntakeQuantity() {
		return this.intakeQuantity;
	}

	public void setIntakeQuantity(int intakeQuantity) {
		this.intakeQuantity = intakeQuantity;
	}

	public boolean getIsactive() {
		return this.isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public byte getIsReturnInvoiceInitated() {
		return this.isReturnInvoiceInitated;
	}

	public void setIsReturnInvoiceInitated(byte isReturnInvoiceInitated) {
		this.isReturnInvoiceInitated = isReturnInvoiceInitated;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Rawmaterialorderinvoice getRawmaterialorderinvoice() {
		return this.rawmaterialorderinvoice;
	}

	public void setRawmaterialorderinvoice(Rawmaterialorderinvoice rawmaterialorderinvoice) {
		this.rawmaterialorderinvoice = rawmaterialorderinvoice;
	}

	public Rawmaterial getRawmaterial() {
		return this.rawmaterial;
	}

	public void setRawmaterial(Rawmaterial rawmaterial) {
		this.rawmaterial = rawmaterial;
	}

}