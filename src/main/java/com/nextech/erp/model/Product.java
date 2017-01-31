package com.nextech.erp.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

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

	@NotBlank(message="{client part number should not be blank}")
	@Size(min = 1, max = 255, message = "{client part number sholud be greater than 4 or less than 255 characters or digits}")
	private String clientpartnumber;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="created_date")
	private Timestamp createdDate;

	@NotBlank(message="{description should not be blank}")
	@Size(min = 4, max = 255, message = "{description sholud be greater than 4 or less than 255 characters}")
	private String description;

	@NotBlank(message="{design should not be blank}")
	@Size(min = 2, max = 255, message = "{design sholud be greater than 2 or less than 255 characters}")
	private String design;

	private boolean isactive;

	@NotBlank(message="{name should not be blank}")
	@Size(min = 2, max = 255, message = "{name sholud be greater than 2 or less than 255 characters}")
	private String name;

	@NotBlank(message="{part number should not be blank}")
	@Size(min = 1, max = 255, message = "{part number sholud be greater than 1 or less than 255 characters or digits}")
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

	//bi-directional many-to-one association to Productrawmaterialassociation
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
	private List<Productrawmaterialassociation> productrawmaterialassociations;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
	private List<Orderproductassociation> orderproductassociations;
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
	public List<Orderproductassociation> getOrderproductassociations() {
		return orderproductassociations;
	}
	public void setOrderproductassociations(
			List<Orderproductassociation> orderproductassociations) {
		this.orderproductassociations = orderproductassociations;
	}

}