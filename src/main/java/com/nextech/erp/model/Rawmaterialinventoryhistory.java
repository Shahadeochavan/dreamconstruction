package com.nextech.erp.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the rawmaterialinventoryhistory database table.
 * 
 */
@Entity
@NamedQuery(name="Rawmaterialinventoryhistory.findAll", query="SELECT r FROM Rawmaterialinventoryhistory r")
public class Rawmaterialinventoryhistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="created_date")
	private Timestamp createdDate;

	private String description;

	private boolean isactive;

	@Column(name="updated_by")
	private int updatedBy;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	//bi-directional many-to-one association to Qualitycheckrawmaterial
	@ManyToOne
	@JoinColumn(name="rmqualitycheckid")
	private Qualitycheckrawmaterial qualitycheckrawmaterial;

	//bi-directional many-to-one association to Rawmaterialinventory
	@ManyToOne
	private Rawmaterialinventory rawmaterialinventory;

	//bi-directional many-to-one association to Status
	@ManyToOne
	private Status status;

	public Rawmaterialinventoryhistory() {
	}
	public Rawmaterialinventoryhistory(int id) {
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getIsactive() {
		return this.isactive;
	}

	public void setIsactive(boolean isactive) {
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

	public Qualitycheckrawmaterial getQualitycheckrawmaterial() {
		return this.qualitycheckrawmaterial;
	}

	public void setQualitycheckrawmaterial(Qualitycheckrawmaterial qualitycheckrawmaterial) {
		this.qualitycheckrawmaterial = qualitycheckrawmaterial;
	}

	public Rawmaterialinventory getRawmaterialinventory() {
		return this.rawmaterialinventory;
	}

	public void setRawmaterialinventory(Rawmaterialinventory rawmaterialinventory) {
		this.rawmaterialinventory = rawmaterialinventory;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}