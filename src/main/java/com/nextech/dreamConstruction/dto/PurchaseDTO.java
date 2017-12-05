package com.nextech.dreamConstruction.dto;

import java.util.Date;
import java.util.List;


public class PurchaseDTO extends AbstractDTO{

	private String name;
	private long purchaseId;
	private Date expectedDeliveryDate;	
	private List<PurchaseAssnDTO> purchaseAssnDTOs;
	private long vendorId;
	
	public	PurchaseDTO(){
		
	}
	public  PurchaseDTO(int id){
		this.setId(id);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<PurchaseAssnDTO> getPurchaseAssnDTOs() {
		return purchaseAssnDTOs;
	}
	public void setPurchaseAssnDTOs(List<PurchaseAssnDTO> purchaseAssnDTOs) {
		this.purchaseAssnDTOs = purchaseAssnDTOs;
	}
	public Date getExpectedDeliveryDate() {
		return expectedDeliveryDate;
	}
	public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
		this.expectedDeliveryDate = expectedDeliveryDate;
	}
	public long getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(long purchaseId) {
		this.purchaseId = purchaseId;
	}
	public long getVendorId() {
		return vendorId;
	}
	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}
	
}
