package com.nextech.erp.dto;

public class ProductOrderInventoryData {
	private String partNumber;
	private long remainingQuantity;
	private long availableQuantity;

	
	
	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public long getRemainingQuantity() {
		return remainingQuantity;
	}

	public void setRemainingQuantity(long remainingQuantity) {
		this.remainingQuantity = remainingQuantity;
	}

	public long getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(long availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

}
