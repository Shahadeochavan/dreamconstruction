package com.nextech.erp.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String clientpartnumber;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="created_date")
	private Timestamp createdDate;

	private String description;

	private String design;

	private boolean isactive;

	private String name;

	@Column(name="part_number")
	private String partNumber;

	@Column(name="updated_by")
	private int updatedBy;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	//bi-directional many-to-one association to Productinventory
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
	private List<Productinventory> productinventories;

	//bi-directional many-to-one association to Productorder
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
	private List<Productorder> productorders;

	//bi-directional many-to-one association to Productrawmaterialassociation
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
	private List<Productrawmaterialassociation> productrawmaterialassociations;

	public Product() {
	}
	public Product(int id) {
		this.id=id;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClientpartnumber() {
		return this.clientpartnumber;
	}

	public void setClientpartnumber(String clientpartnumber) {
		this.clientpartnumber = clientpartnumber;
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

	public String getDesign() {
		return this.design;
	}

	public void setDesign(String design) {
		this.design = design;
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

	public List<Productinventory> getProductinventories() {
		return this.productinventories;
	}

	public void setProductinventories(List<Productinventory> productinventories) {
		this.productinventories = productinventories;
	}

	public Productinventory addProductinventory(Productinventory productinventory) {
		getProductinventories().add(productinventory);
		productinventory.setProduct(this);

		return productinventory;
	}

	public Productinventory removeProductinventory(Productinventory productinventory) {
		getProductinventories().remove(productinventory);
		productinventory.setProduct(null);

		return productinventory;
	}

	public List<Productorder> getProductorders() {
		return this.productorders;
	}

	public void setProductorders(List<Productorder> productorders) {
		this.productorders = productorders;
	}

	public Productorder addProductorder(Productorder productorder) {
		getProductorders().add(productorder);
		productorder.setProduct(this);

		return productorder;
	}

	public Productorder removeProductorder(Productorder productorder) {
		getProductorders().remove(productorder);
		productorder.setProduct(null);

		return productorder;
	}

	public List<Productrawmaterialassociation> getProductrawmaterialassociations() {
		return this.productrawmaterialassociations;
	}

	public void setProductrawmaterialassociations(List<Productrawmaterialassociation> productrawmaterialassociations) {
		this.productrawmaterialassociations = productrawmaterialassociations;
	}

	public Productrawmaterialassociation addProductrawmaterialassociation(Productrawmaterialassociation productrawmaterialassociation) {
		getProductrawmaterialassociations().add(productrawmaterialassociation);
		productrawmaterialassociation.setProduct(this);

		return productrawmaterialassociation;
	}

	public Productrawmaterialassociation removeProductrawmaterialassociation(Productrawmaterialassociation productrawmaterialassociation) {
		getProductrawmaterialassociations().remove(productrawmaterialassociation);
		productrawmaterialassociation.setProduct(null);

		return productrawmaterialassociation;
	}

}