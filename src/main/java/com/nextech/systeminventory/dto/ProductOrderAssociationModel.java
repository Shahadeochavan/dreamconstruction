package com.nextech.systeminventory.dto;

import java.sql.Date;
import java.util.List;

import com.nextech.systeminventory.model.Productorderassociation;


public class ProductOrderAssociationModel {
	
	private String description;
	private long clientId;
	private Date expecteddeliveryDate;	
	private Date createDate;
	private String invoiceNo;
	private List<Productorderassociation> productorderassciation;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getExpecteddeliveryDate() {
		return expecteddeliveryDate;
	}
	public void setExpecteddeliveryDate(Date expecteddeliveryDate) {
		this.expecteddeliveryDate = expecteddeliveryDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public List<Productorderassociation> getProductorderassciation() {
		return productorderassciation;
	}
	public void setProductorderassciation(
			List<Productorderassociation> productorderassciation) {
		this.productorderassciation = productorderassciation;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public long getClientId() {
		return clientId;
	}
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}	
	
}
