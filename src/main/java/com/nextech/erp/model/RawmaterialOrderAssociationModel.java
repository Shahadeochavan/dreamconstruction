package com.nextech.erp.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class RawmaterialOrderAssociationModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2264984414287739733L;
	private String description;
	private Date deliveryDate;	
	private Date createDate;
	private long Vendor;
	private List<Rawmaterialorderassociation> rawmaterialorderassociations;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public List<Rawmaterialorderassociation> getRawmaterialorderassociations() {
		return rawmaterialorderassociations;
	}
	public void setRawmaterialorderassociations(
			List<Rawmaterialorderassociation> rawmaterialorderassociations) {
		this.rawmaterialorderassociations = rawmaterialorderassociations;
	}
	public long getVendor() {
		return Vendor;
	}
	public void setVendor(long vendor) {
		Vendor = vendor;
	}

}
