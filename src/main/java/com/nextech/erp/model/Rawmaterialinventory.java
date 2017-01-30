package com.nextech.erp.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the rawmaterialinventory database table.
 * 
 */
@Entity
@NamedQuery(name="Rawmaterialinventory.findAll", query="SELECT r FROM Rawmaterialinventory r")
public class Rawmaterialinventory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="created_date")
	private Timestamp createdDate;

	private String description;

	private boolean isactive;

	private String name;

	private int quantityAvailable;

	private int racknumber;

	@Column(name="updated_by")
	private int updatedBy;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	//bi-directional many-to-one association to Rawmaterial
	@ManyToOne
	private Rawmaterial rawmaterial;

	//bi-directional many-to-one association to Rawmaterialinventoryhistory
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rawmaterialinventory", cascade = CascadeType.ALL)
	private List<Rawmaterialinventoryhistory> rawmaterialinventoryhistories;

	public Rawmaterialinventory() {
	}
	public Rawmaterialinventory(int id) {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantityAvailable() {
		return this.quantityAvailable;
	}

	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public int getRacknumber() {
		return this.racknumber;
	}

	public void setRacknumber(int racknumber) {
		this.racknumber = racknumber;
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

	public Rawmaterial getRawmaterial() {
		return this.rawmaterial;
	}

	public void setRawmaterial(Rawmaterial rawmaterial) {
		this.rawmaterial = rawmaterial;
	}

	public List<Rawmaterialinventoryhistory> getRawmaterialinventoryhistories() {
		return this.rawmaterialinventoryhistories;
	}

	public void setRawmaterialinventoryhistories(List<Rawmaterialinventoryhistory> rawmaterialinventoryhistories) {
		this.rawmaterialinventoryhistories = rawmaterialinventoryhistories;
	}

	public Rawmaterialinventoryhistory addRawmaterialinventoryhistory(Rawmaterialinventoryhistory rawmaterialinventoryhistory) {
		getRawmaterialinventoryhistories().add(rawmaterialinventoryhistory);
		rawmaterialinventoryhistory.setRawmaterialinventory(this);

		return rawmaterialinventoryhistory;
	}

	public Rawmaterialinventoryhistory removeRawmaterialinventoryhistory(Rawmaterialinventoryhistory rawmaterialinventoryhistory) {
		getRawmaterialinventoryhistories().remove(rawmaterialinventoryhistory);
		rawmaterialinventoryhistory.setRawmaterialinventory(null);

		return rawmaterialinventoryhistory;
	}

}