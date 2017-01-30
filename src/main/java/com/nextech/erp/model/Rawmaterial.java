package com.nextech.erp.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the rawmaterial database table.
 * 
 */
@Entity
@NamedQuery(name="Rawmaterial.findAll", query="SELECT r FROM Rawmaterial r")
public class Rawmaterial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="created_date")
	private Timestamp createdDate;

	@NotBlank(message="{description should not be blank}")
	@Size(min = 4, max = 255, message = "{description sholud be greater than 4 or less than 255 characters}")
	private String description;

	private boolean isactive;

	private String name;

	@Column(name="part_number")
	private String partNumber;

	@Column(name="price_per_unit")
	private float pricePerUnit;

	@Column(name="updated_by")
	private int updatedBy;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	//bi-directional many-to-one association to Productrawmaterialassociation
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rawmaterial", cascade = CascadeType.ALL)
	private List<Productrawmaterialassociation> productrawmaterialassociations;

	//bi-directional many-to-one association to Unit
	@ManyToOne
	private Unit unit;

	//bi-directional many-to-one association to Rawmaterialinventory
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rawmaterial", cascade = CascadeType.ALL)
	private List<Rawmaterialinventory> rawmaterialinventories;

	//bi-directional many-to-one association to Rawmaterialorder
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rawmaterial", cascade = CascadeType.ALL)
	private List<Rawmaterialorder> rawmaterialorders;

	//bi-directional many-to-one association to Rawmaterialvendorassociation
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rawmaterial", cascade = CascadeType.ALL)
	private List<Rawmaterialvendorassociation> rawmaterialvendorassociations;

	public Rawmaterial() {
	}
	public Rawmaterial(int id) {
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

	public String getPartNumber() {
		return this.partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public float getPricePerUnit() {
		return this.pricePerUnit;
	}

	public void setPricePerUnit(float pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
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

	public List<Productrawmaterialassociation> getProductrawmaterialassociations() {
		return this.productrawmaterialassociations;
	}

	public void setProductrawmaterialassociations(List<Productrawmaterialassociation> productrawmaterialassociations) {
		this.productrawmaterialassociations = productrawmaterialassociations;
	}

	public Productrawmaterialassociation addProductrawmaterialassociation(Productrawmaterialassociation productrawmaterialassociation) {
		getProductrawmaterialassociations().add(productrawmaterialassociation);
		productrawmaterialassociation.setRawmaterial(this);

		return productrawmaterialassociation;
	}

	public Productrawmaterialassociation removeProductrawmaterialassociation(Productrawmaterialassociation productrawmaterialassociation) {
		getProductrawmaterialassociations().remove(productrawmaterialassociation);
		productrawmaterialassociation.setRawmaterial(null);

		return productrawmaterialassociation;
	}

	public Unit getUnit() {
		return this.unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public List<Rawmaterialinventory> getRawmaterialinventories() {
		return this.rawmaterialinventories;
	}

	public void setRawmaterialinventories(List<Rawmaterialinventory> rawmaterialinventories) {
		this.rawmaterialinventories = rawmaterialinventories;
	}

	public Rawmaterialinventory addRawmaterialinventory(Rawmaterialinventory rawmaterialinventory) {
		getRawmaterialinventories().add(rawmaterialinventory);
		rawmaterialinventory.setRawmaterial(this);

		return rawmaterialinventory;
	}

	public Rawmaterialinventory removeRawmaterialinventory(Rawmaterialinventory rawmaterialinventory) {
		getRawmaterialinventories().remove(rawmaterialinventory);
		rawmaterialinventory.setRawmaterial(null);

		return rawmaterialinventory;
	}

	public List<Rawmaterialorder> getRawmaterialorders() {
		return this.rawmaterialorders;
	}

	public void setRawmaterialorders(List<Rawmaterialorder> rawmaterialorders) {
		this.rawmaterialorders = rawmaterialorders;
	}

	public Rawmaterialorder addRawmaterialorder(Rawmaterialorder rawmaterialorder) {
		getRawmaterialorders().add(rawmaterialorder);
		rawmaterialorder.setRawmaterial(this);

		return rawmaterialorder;
	}

	public Rawmaterialorder removeRawmaterialorder(Rawmaterialorder rawmaterialorder) {
		getRawmaterialorders().remove(rawmaterialorder);
		rawmaterialorder.setRawmaterial(null);

		return rawmaterialorder;
	}

	public List<Rawmaterialvendorassociation> getRawmaterialvendorassociations() {
		return this.rawmaterialvendorassociations;
	}

	public void setRawmaterialvendorassociations(List<Rawmaterialvendorassociation> rawmaterialvendorassociations) {
		this.rawmaterialvendorassociations = rawmaterialvendorassociations;
	}

	public Rawmaterialvendorassociation addRawmaterialvendorassociation(Rawmaterialvendorassociation rawmaterialvendorassociation) {
		getRawmaterialvendorassociations().add(rawmaterialvendorassociation);
		rawmaterialvendorassociation.setRawmaterial(this);

		return rawmaterialvendorassociation;
	}

	public Rawmaterialvendorassociation removeRawmaterialvendorassociation(Rawmaterialvendorassociation rawmaterialvendorassociation) {
		getRawmaterialvendorassociations().remove(rawmaterialvendorassociation);
		rawmaterialvendorassociation.setRawmaterial(null);

		return rawmaterialvendorassociation;
	}

}