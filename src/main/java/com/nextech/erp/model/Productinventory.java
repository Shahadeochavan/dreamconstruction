package com.nextech.erp.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the productinventory database table.
 * 
 */
@Entity
@NamedQuery(name="Productinventory.findAll", query="SELECT p FROM Productinventory p")
public class Productinventory implements Serializable {
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

	private int quantityavailable;

	private int racknumber;

	@Column(name="updated_by")
	private int updatedBy;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="productid")
	private Product product;

	//bi-directional many-to-one association to Productinventoryhistory
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productinventory", cascade = CascadeType.ALL)
	private List<Productinventoryhistory> productinventoryhistories;

	public Productinventory() {
	}
	public Productinventory(int id) {
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

	public int getQuantityavailable() {
		return this.quantityavailable;
	}

	public void setQuantityavailable(int quantityavailable) {
		this.quantityavailable = quantityavailable;
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

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Productinventoryhistory> getProductinventoryhistories() {
		return this.productinventoryhistories;
	}

	public void setProductinventoryhistories(List<Productinventoryhistory> productinventoryhistories) {
		this.productinventoryhistories = productinventoryhistories;
	}

	public Productinventoryhistory addProductinventoryhistory(Productinventoryhistory productinventoryhistory) {
		getProductinventoryhistories().add(productinventoryhistory);
		productinventoryhistory.setProductinventory(this);

		return productinventoryhistory;
	}

	public Productinventoryhistory removeProductinventoryhistory(Productinventoryhistory productinventoryhistory) {
		getProductinventoryhistories().remove(productinventoryhistory);
		productinventoryhistory.setProductinventory(null);

		return productinventoryhistory;
	}

}