package com.nextech.systeminventory.dto;

import java.util.Date;
import java.util.List;

import com.nextech.systeminventory.model.Contractor;


public class ProductOrderDTO extends AbstractDTO {
	
	private Contractor contractorId;
	private Date expecteddeliveryDate;	
	private Date createDate;
	private String invoiceNo;
	private float totalPrice;
	private float actualPrice;
	private String poNO;
	private float tax;
	private long statusId;
	
	private List<ProductOrderAssociationDTO> productOrderAssociationDTOs;
	
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
	
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Contractor getContractorId() {
		return contractorId;
	}
	public void setContractorId(Contractor contractorId) {
		this.contractorId = contractorId;
	}
	public List<ProductOrderAssociationDTO> getProductOrderAssociationDTOs() {
		return productOrderAssociationDTOs;
	}
	public void setProductOrderAssociationDTOs(
			List<ProductOrderAssociationDTO> productOrderAssociationDTOs) {
		this.productOrderAssociationDTOs = productOrderAssociationDTOs;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public float getActualPrice() {
		return actualPrice;
	}
	public void setActualPrice(float actualPrice) {
		this.actualPrice = actualPrice;
	}
	public float getTax() {
		return tax;
	}
	public void setTax(float tax) {
		this.tax = tax;
	}
	public long getStatusId() {
		return statusId;
	}
	public void setStatusId(long statusId) {
		this.statusId = statusId;
	}
	public String getPoNO() {
		return poNO;
	}
	public void setPoNO(String poNO) {
		this.poNO = poNO;
	}
	
}
