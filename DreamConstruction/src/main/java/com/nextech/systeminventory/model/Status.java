package com.nextech.systeminventory.model;

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


	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "status", cascade = CascadeType.ALL)
	private List<Productinventoryhistory> productinventoryhistories;

	//bi-directional many-to-one association to Productorder
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "status", cascade = CascadeType.ALL)
	private List<Productorder> productorders;

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
}