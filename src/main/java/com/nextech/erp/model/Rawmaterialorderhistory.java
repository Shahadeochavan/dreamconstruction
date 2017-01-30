package com.nextech.erp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the rawmaterialorderhistory database table.
 * 
 */
@Entity
@NamedQuery(name="Rawmaterialorderhistory.findAll", query="SELECT r FROM Rawmaterialorderhistory r")
public class Rawmaterialorderhistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String comment;

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

	//bi-directional many-to-one association to Rawmaterialorder
	@ManyToOne
	private Rawmaterialorder rawmaterialorder;

	//bi-directional many-to-one association to Statustransition
	@ManyToOne
	@JoinColumn(name="status_id_from")
	private Statustransition statustransition1;

	//bi-directional many-to-one association to Statustransition
	@ManyToOne
	@JoinColumn(name="status_id_to")
	private Statustransition statustransition2;

	public Rawmaterialorderhistory() {
	}

	public Rawmaterialorderhistory(int id) {
		this.id=id;
	}
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public Rawmaterialorder getRawmaterialorder() {
		return this.rawmaterialorder;
	}

	public void setRawmaterialorder(Rawmaterialorder rawmaterialorder) {
		this.rawmaterialorder = rawmaterialorder;
	}

	public Statustransition getStatustransition1() {
		return this.statustransition1;
	}

	public void setStatustransition1(Statustransition statustransition1) {
		this.statustransition1 = statustransition1;
	}

	public Statustransition getStatustransition2() {
		return this.statustransition2;
	}

	public void setStatustransition2(Statustransition statustransition2) {
		this.statustransition2 = statustransition2;
	}

}