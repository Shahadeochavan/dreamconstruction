package com.nextech.dreamConstruction.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the purchase database table.
 * 
 */
@Entity
@NamedQuery(name="Purchase.findAll", query="SELECT p FROM Purchase p")
public class Purchase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="created_date")
	private Timestamp createdDate;

	private String description;

	private boolean isactive;

	private String name;

	private int quantity;

	@Column(name="updated_by")
	private String updatedBy;

	@Column(name="updated_date")
	private Timestamp updatedDate;
	
	@Temporal(TemporalType.DATE)
	private Date expecteddeliveryDate;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="statusId")
	private Status status;

	//bi-directional many-to-one association to PurchaseAssn
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "purchase", cascade = CascadeType.ALL)
	private List<PurchaseAssn> purchaseAssns;
	
	@ManyToOne
	@JoinColumn(name="vendorId")
	private Vendor vendor;

	public Purchase() {
	}
	
	public Purchase(int id) {
		this.id=id;
	}


	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
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

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<PurchaseAssn> getPurchaseAssns() {
		return this.purchaseAssns;
	}

	public void setPurchaseAssns(List<PurchaseAssn> purchaseAssns) {
		this.purchaseAssns = purchaseAssns;
	}

	public PurchaseAssn addPurchaseAssn(PurchaseAssn purchaseAssn) {
		getPurchaseAssns().add(purchaseAssn);
		purchaseAssn.setPurchase(this);

		return purchaseAssn;
	}

	public PurchaseAssn removePurchaseAssn(PurchaseAssn purchaseAssn) {
		getPurchaseAssns().remove(purchaseAssn);
		purchaseAssn.setPurchase(null);

		return purchaseAssn;
	}

	public Date getExpecteddeliveryDate() {
		return expecteddeliveryDate;
	}

	public void setExpecteddeliveryDate(Date expecteddeliveryDate) {
		this.expecteddeliveryDate = expecteddeliveryDate;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

}