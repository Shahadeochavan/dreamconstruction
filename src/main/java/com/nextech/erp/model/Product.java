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
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;

	private String clientpartnumber;

	@Column(name="created_by")
	private long createdBy;

	@Column(name="created_date")
	private Timestamp createdDate;

	private String description;

	private String design;

	private boolean isactive;

	private String name;

	@Column(name="part_number")
	private String partNumber;

	@Column(name="updated_by")
	private long updatedBy;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	//bi-directional many-to-one association to Dispatch
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
	private List<Dispatch> dispatches;

	//bi-directional many-to-one association to Productinventory
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
	private List<Productinventory> productinventories;

	//bi-directional many-to-one association to Productionplanning
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
	private List<Productionplanning> productionplannings;

	//bi-directional many-to-one association to Productorderassociation
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
	private List<Productorderassociation> productorderassociations;

	//bi-directional many-to-one association to Productquality
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
	private List<Productquality> productqualities;

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

	public List<Dispatch> getDispatches() {
		return this.dispatches;
	}

	public void setDispatches(List<Dispatch> dispatches) {
		this.dispatches = dispatches;
	}

	public Dispatch addDispatch(Dispatch dispatch) {
		getDispatches().add(dispatch);
		dispatch.setProduct(this);

		return dispatch;
	}

	public Dispatch removeDispatch(Dispatch dispatch) {
		getDispatches().remove(dispatch);
		dispatch.setProduct(null);

		return dispatch;
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

	public List<Productionplanning> getProductionplannings() {
		return this.productionplannings;
	}

	public void setProductionplannings(List<Productionplanning> productionplannings) {
		this.productionplannings = productionplannings;
	}

	public Productionplanning addProductionplanning(Productionplanning productionplanning) {
		getProductionplannings().add(productionplanning);
		productionplanning.setProduct(this);

		return productionplanning;
	}

	public Productionplanning removeProductionplanning(Productionplanning productionplanning) {
		getProductionplannings().remove(productionplanning);
		productionplanning.setProduct(null);

		return productionplanning;
	}

	public List<Productorderassociation> getProductorderassociations() {
		return this.productorderassociations;
	}

	public void setProductorderassociations(List<Productorderassociation> productorderassociations) {
		this.productorderassociations = productorderassociations;
	}

	public Productorderassociation addProductorderassociation(Productorderassociation productorderassociation) {
		getProductorderassociations().add(productorderassociation);
		productorderassociation.setProduct(this);

		return productorderassociation;
	}

	public Productorderassociation removeProductorderassociation(Productorderassociation productorderassociation) {
		getProductorderassociations().remove(productorderassociation);
		productorderassociation.setProduct(null);

		return productorderassociation;
	}

	public List<Productquality> getProductqualities() {
		return this.productqualities;
	}

	public void setProductqualities(List<Productquality> productqualities) {
		this.productqualities = productqualities;
	}

	public Productquality addProductquality(Productquality productquality) {
		getProductqualities().add(productquality);
		productquality.setProduct(this);

		return productquality;
	}

	public Productquality removeProductquality(Productquality productquality) {
		getProductqualities().remove(productquality);
		productquality.setProduct(null);

		return productquality;
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