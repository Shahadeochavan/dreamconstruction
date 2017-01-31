package com.nextech.erp.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class ProductOrderAssociationModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2264984414287739733L;
	private String description;
	private long client;
	private Date deliveryDate;	
	private Date createDate;	
	private List<Orderproductassociation> orderproductassociations;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getClient() {
		return client;
	}
	public void setClient(long client) {
		this.client = client;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public List<Orderproductassociation> getOrderproductassociations() {
		return orderproductassociations;
	}
	public void setOrderproductassociations(
			List<Orderproductassociation> orderproductassociations) {
		this.orderproductassociations = orderproductassociations;
	}	
	
}
