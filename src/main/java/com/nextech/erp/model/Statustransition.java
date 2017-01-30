package com.nextech.erp.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the statustransition database table.
 * 
 */
@Entity
@NamedQuery(name="Statustransition.findAll", query="SELECT s FROM Statustransition s")
public class Statustransition implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="created_date")
	private Timestamp createdDate;

	private String description;

	@Column(name="from_status")
	private String fromStatus;

	private boolean isactive;

	private String isNotificationEmail;

	private String isNotificationSMS;

	private String name;

	@Column(name="to_status")
	private String toStatus;

	@Column(name="updated_by")
	private int updatedBy;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	//bi-directional many-to-one association to Rawmaterialorderhistory
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "statustransition1", cascade = CascadeType.ALL)
	private List<Rawmaterialorderhistory> rawmaterialorderhistories1;

	//bi-directional many-to-one association to Rawmaterialorderhistory
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "statustransition2", cascade = CascadeType.ALL)
	private List<Rawmaterialorderhistory> rawmaterialorderhistories2;

	public Statustransition(int id) {
		this.id=id;
	}
	public Statustransition() {
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

	public String getFromStatus() {
		return this.fromStatus;
	}

	public void setFromStatus(String fromStatus) {
		this.fromStatus = fromStatus;
	}

	public boolean getIsactive() {
		return this.isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public String getIsNotificationEmail() {
		return this.isNotificationEmail;
	}

	public void setIsNotificationEmail(String isNotificationEmail) {
		this.isNotificationEmail = isNotificationEmail;
	}

	public String getIsNotificationSMS() {
		return this.isNotificationSMS;
	}

	public void setIsNotificationSMS(String isNotificationSMS) {
		this.isNotificationSMS = isNotificationSMS;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getToStatus() {
		return this.toStatus;
	}

	public void setToStatus(String toStatus) {
		this.toStatus = toStatus;
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

	public List<Rawmaterialorderhistory> getRawmaterialorderhistories1() {
		return this.rawmaterialorderhistories1;
	}

	public void setRawmaterialorderhistories1(List<Rawmaterialorderhistory> rawmaterialorderhistories1) {
		this.rawmaterialorderhistories1 = rawmaterialorderhistories1;
	}

	public Rawmaterialorderhistory addRawmaterialorderhistories1(Rawmaterialorderhistory rawmaterialorderhistories1) {
		getRawmaterialorderhistories1().add(rawmaterialorderhistories1);
		rawmaterialorderhistories1.setStatustransition1(this);

		return rawmaterialorderhistories1;
	}

	public Rawmaterialorderhistory removeRawmaterialorderhistories1(Rawmaterialorderhistory rawmaterialorderhistories1) {
		getRawmaterialorderhistories1().remove(rawmaterialorderhistories1);
		rawmaterialorderhistories1.setStatustransition1(null);

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
		rawmaterialorderhistories2.setStatustransition2(this);

		return rawmaterialorderhistories2;
	}

	public Rawmaterialorderhistory removeRawmaterialorderhistories2(Rawmaterialorderhistory rawmaterialorderhistories2) {
		getRawmaterialorderhistories2().remove(rawmaterialorderhistories2);
		rawmaterialorderhistories2.setStatustransition2(null);

		return rawmaterialorderhistories2;
	}

}