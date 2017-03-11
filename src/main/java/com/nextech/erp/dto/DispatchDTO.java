package com.nextech.erp.dto;

import java.util.List;

public class DispatchDTO {
	private long orderId;
	private String invoiceNo;
	private List<Part> parts;
	
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public List<Part> getParts() {
		return parts;
	}
	public void setParts(List<Part> parts) {
		this.parts = parts;
	}
}
