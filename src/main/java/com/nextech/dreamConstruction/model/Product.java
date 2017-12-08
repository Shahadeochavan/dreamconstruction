package com.nextech.dreamConstruction.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Size;

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

	@Column(name="created_by")
	private long createdBy;

	@Column(name="created_date")
	private Timestamp createdDate;

	@Size(min = 2, max = 255, message = "{Description sholud be greater than 4 or less than 255 characters}")
	private String description;

	private String design;

	private boolean isactive;
	
	private float pricePerUnit;

	@Size(min = 2, max = 255, message = "{Name sholud be greater than 2 or less than 255 characters}")
	private String name;

	private String productCode;
	
	private String hsnCode;
	
	private double gst;

	@Column(name="updated_by")
	private long updatedBy;

	@Column(name="updated_date")
	private Timestamp updatedDate;
	
	//bi-directional many-to-one association to Productinventory
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
	private List<Productinventory> productinventories;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
	private List<PrVndrAssn> prVndrAssns;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
	private List<PurchaseAssn> purchaseAssns;
	
	@ManyToOne
	@JoinColumn(name="unitId")
	private Unit unit;


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
	

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
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

	public float getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(float pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public List<PrVndrAssn> getPrVndrAssns() {
		return this.prVndrAssns;
	}

	public void setPrVndrAssns(List<PrVndrAssn> prVndrAssns) {
		this.prVndrAssns = prVndrAssns;
	}

	public PrVndrAssn addPrVndrAssn(PrVndrAssn prVndrAssn) {
		getPrVndrAssns().add(prVndrAssn);
		prVndrAssn.setProduct(this);

		return prVndrAssn;
	}

	public PrVndrAssn removePrVndrAssn(PrVndrAssn prVndrAssn) {
		getPrVndrAssns().remove(prVndrAssn);
		prVndrAssn.setProduct(null);

		return prVndrAssn;
	}

	public List<PurchaseAssn> getPurchaseAssns() {
		return purchaseAssns;
	}

	public void setPurchaseAssns(List<PurchaseAssn> purchaseAssns) {
		this.purchaseAssns = purchaseAssns;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	public double getGst() {
		return gst;
	}

	public void setGst(double gst) {
		this.gst = gst;
	}
	public Unit getUnit() {
		return this.unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}
}