package com.nextech.systeminventory.dto;

import java.util.Date;
import java.util.List;


public class ProductOrderDTO extends AbstractDTO {
	
	private long clientId;
	private Date expecteddeliveryDate;	
	private Date createDate;
	private String invoiceNo;
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
	public long getClientId() {
		return clientId;
	}
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
	public List<ProductOrderAssociationDTO> getProductOrderAssociationDTOs() {
		return productOrderAssociationDTOs;
	}
	public void setProductOrderAssociationDTOs(
			List<ProductOrderAssociationDTO> productOrderAssociationDTOs) {
		this.productOrderAssociationDTOs = productOrderAssociationDTOs;
	}	
}
