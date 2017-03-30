package com.nextech.erp.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the status database table.
 * 
 */
@Entity
@NamedQuery(name="Status.findAll", query="SELECT s FROM Status s")
public class Status implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;

	@Column(name="created_by")
	private long createdBy;

	@Column(name="created_date")
	private Timestamp createdDate;

	private String description;

	private boolean isactive;

	private String name;

	private String type;

	@Column(name="updated_by")
	private long updatedBy;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	//bi-directional many-to-one association to Productinventoryhistory
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "status", cascade = CascadeType.ALL)
	private List<Productinventoryhistory> productinventoryhistories;

	//bi-directional many-to-one association to Productionplanning
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "status", cascade = CascadeType.ALL)
	private List<Productionplanning> productionplannings;

	//bi-directional many-to-one association to Productorder
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "status", cascade = CascadeType.ALL)
	private List<Productorder> productorders;

	//bi-directional many-to-one association to Rawmaterialinventoryhistory
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "status", cascade = CascadeType.ALL)
	private List<Rawmaterialinventoryhistory> rawmaterialinventoryhistories;

	//bi-directional many-to-one association to Rawmaterialorder
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "status", cascade = CascadeType.ALL)
	private List<Rawmaterialorder> rawmaterialorders;

	//bi-directional many-to-one association to Rawmaterialorderhistory
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "status1", cascade = CascadeType.ALL)
	private List<Rawmaterialorderhistory> rawmaterialorderhistories1;

	//bi-directional many-to-one association to Rawmaterialorderhistory
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "status2", cascade = CascadeType.ALL)
	private List<Rawmaterialorderhistory> rawmaterialorderhistories2;

	//bi-directional many-to-one association to Rawmaterialorderinvoice
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "status", cascade = CascadeType.ALL)
	private List<Rawmaterialorderinvoice> rawmaterialorderinvoices;

	//bi-directional many-to-one association to Dailyproduction
//	@OneToMany(mappedBy="status")
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "status", cascade = CascadeType.ALL)
	private List<Dailyproduction> dailyproductions;

	public Status() {
	}
	public Status(int id) {
		this.id=id;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
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

	public List<Productinventoryhistory> getProductinventoryhistories() {
		return this.productinventoryhistories;
	}

	public void setProductinventoryhistories(List<Productinventoryhistory> productinventoryhistories) {
		this.productinventoryhistories = productinventoryhistories;
	}

	public Productinventoryhistory addProductinventoryhistory(Productinventoryhistory productinventoryhistory) {
		getProductinventoryhistories().add(productinventoryhistory);
		productinventoryhistory.setStatus(this);

		return productinventoryhistory;
	}

	public Productinventoryhistory removeProductinventoryhistory(Productinventoryhistory productinventoryhistory) {
		getProductinventoryhistories().remove(productinventoryhistory);
		productinventoryhistory.setStatus(null);

		return productinventoryhistory;
	}

	public List<Productionplanning> getProductionplannings() {
		return this.productionplannings;
	}

	public void setProductionplannings(List<Productionplanning> productionplannings) {
		this.productionplannings = productionplannings;
	}

	public Productionplanning addProductionplanning(Productionplanning productionplanning) {
		getProductionplannings().add(productionplanning);
		productionplanning.setStatus(this);

		return productionplanning;
	}

	public Productionplanning removeProductionplanning(Productionplanning productionplanning) {
		getProductionplannings().remove(productionplanning);
		productionplanning.setStatus(null);

		return productionplanning;
	}

	public List<Productorder> getProductorders() {
		return this.productorders;
	}

	public void setProductorders(List<Productorder> productorders) {
		this.productorders = productorders;
	}

	public Productorder addProductorder(Productorder productorder) {
		getProductorders().add(productorder);
		productorder.setStatus(this);

		return productorder;
	}

	public Productorder removeProductorder(Productorder productorder) {
		getProductorders().remove(productorder);
		productorder.setStatus(null);

		return productorder;
	}

	public List<Rawmaterialinventoryhistory> getRawmaterialinventoryhistories() {
		return this.rawmaterialinventoryhistories;
	}

	public void setRawmaterialinventoryhistories(List<Rawmaterialinventoryhistory> rawmaterialinventoryhistories) {
		this.rawmaterialinventoryhistories = rawmaterialinventoryhistories;
	}

	public Rawmaterialinventoryhistory addRawmaterialinventoryhistory(Rawmaterialinventoryhistory rawmaterialinventoryhistory) {
		getRawmaterialinventoryhistories().add(rawmaterialinventoryhistory);
		rawmaterialinventoryhistory.setStatus(this);

		return rawmaterialinventoryhistory;
	}

	public Rawmaterialinventoryhistory removeRawmaterialinventoryhistory(Rawmaterialinventoryhistory rawmaterialinventoryhistory) {
		getRawmaterialinventoryhistories().remove(rawmaterialinventoryhistory);
		rawmaterialinventoryhistory.setStatus(null);

		return rawmaterialinventoryhistory;
	}

	public List<Rawmaterialorder> getRawmaterialorders() {
		return this.rawmaterialorders;
	}

	public void setRawmaterialorders(List<Rawmaterialorder> rawmaterialorders) {
		this.rawmaterialorders = rawmaterialorders;
	}

	public Rawmaterialorder addRawmaterialorder(Rawmaterialorder rawmaterialorder) {
		getRawmaterialorders().add(rawmaterialorder);
		rawmaterialorder.setStatus(this);

		return rawmaterialorder;
	}

	public Rawmaterialorder removeRawmaterialorder(Rawmaterialorder rawmaterialorder) {
		getRawmaterialorders().remove(rawmaterialorder);
		rawmaterialorder.setStatus(null);

		return rawmaterialorder;
	}

	public List<Rawmaterialorderhistory> getRawmaterialorderhistories1() {
		return this.rawmaterialorderhistories1;
	}

	public void setRawmaterialorderhistories1(List<Rawmaterialorderhistory> rawmaterialorderhistories1) {
		this.rawmaterialorderhistories1 = rawmaterialorderhistories1;
	}

	public Rawmaterialorderhistory addRawmaterialorderhistories1(Rawmaterialorderhistory rawmaterialorderhistories1) {
		getRawmaterialorderhistories1().add(rawmaterialorderhistories1);
		rawmaterialorderhistories1.setStatus1(this);

		return rawmaterialorderhistories1;
	}

	public Rawmaterialorderhistory removeRawmaterialorderhistories1(Rawmaterialorderhistory rawmaterialorderhistories1) {
		getRawmaterialorderhistories1().remove(rawmaterialorderhistories1);
		rawmaterialorderhistories1.setStatus1(null);

		return rawmaterialorderhistories1;
	}

	public List<Rawmaterialorderhistory> getRawmaterialorderhistories2() {
		return this.rawmaterialorderhistories2;
	}

	public void setRawmaterialorderhistories2(List<Rawmaterialorderhistory> rawmaterialorderhistories2) {
		this.rawmaterialorderhistories2 = rawmaterialorderhistories2;
	}

	public Rawmaterialorderhistory addRawmaterialorderhistories2(Rawmaterialorderhistory rawmaterialorderhistories2) {
		getRawmaterialorderhistories2().add(rawmaterialorderhistories2);
		rawmaterialorderhistories2.setStatus2(this);

		return rawmaterialorderhistories2;
	}

	public Rawmaterialorderhistory removeRawmaterialorderhistories2(Rawmaterialorderhistory rawmaterialorderhistories2) {
		getRawmaterialorderhistories2().remove(rawmaterialorderhistories2);
		rawmaterialorderhistories2.setStatus2(null);

		return rawmaterialorderhistories2;
	}

	public List<Rawmaterialorderinvoice> getRawmaterialorderinvoices() {
		return this.rawmaterialorderinvoices;
	}

	public void setRawmaterialorderinvoices(List<Rawmaterialorderinvoice> rawmaterialorderinvoices) {
		this.rawmaterialorderinvoices = rawmaterialorderinvoices;
	}

	public Rawmaterialorderinvoice addRawmaterialorderinvoice(Rawmaterialorderinvoice rawmaterialorderinvoice) {
		getRawmaterialorderinvoices().add(rawmaterialorderinvoice);
		rawmaterialorderinvoice.setStatus(this);

		return rawmaterialorderinvoice;
	}

	public Rawmaterialorderinvoice removeRawmaterialorderinvoice(Rawmaterialorderinvoice rawmaterialorderinvoice) {
		getRawmaterialorderinvoices().remove(rawmaterialorderinvoice);
		rawmaterialorderinvoice.setStatus(null);

		return rawmaterialorderinvoice;
	}

	public List<Dailyproduction> getDailyproductions() {
		return this.dailyproductions;
	}

	public void setDailyproductions(List<Dailyproduction> dailyproductions) {
		this.dailyproductions = dailyproductions;
	}

	public Dailyproduction addDailyproduction(Dailyproduction dailyproduction) {
		getDailyproductions().add(dailyproduction);
		dailyproduction.setStatus(this);

		return dailyproduction;
	}

	public Dailyproduction removeDailyproduction(Dailyproduction dailyproduction) {
		getDailyproductions().remove(dailyproduction);
		dailyproduction.setStatus(null);

		return dailyproduction;
	}

}