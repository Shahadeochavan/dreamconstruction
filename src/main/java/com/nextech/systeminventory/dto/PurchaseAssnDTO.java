package com.nextech.systeminventory.dto;

import java.util.Date;




public class PurchaseAssnDTO extends AbstractDTO{

	private int quantity;
	private long purchaseId;
	private ProductDTO productId;
	private int remainingQuantity;
	private Date expectedDeliveryDate;
	private float pricePerUnit;
	
	public	PurchaseAssnDTO(){
		
	}
	public  PurchaseAssnDTO(int id){
		this.setId(id);
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public long getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(long purchaseId) {
		this.purchaseId = purchaseId;
	}
	public ProductDTO getProductId() {
		return productId;
	}
	public void setProductId(ProductDTO productId) {
		this.productId = productId;
	}
	public int getRemainingQuantity() {
		return remainingQuantity;
	}
	public void setRemainingQuantity(int remainingQuantity) {
		this.remainingQuantity = remainingQuantity;
	}
	public Date getExpectedDeliveryDate() {
		return expectedDeliveryDate;
	}
	public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
		this.expectedDeliveryDate = expectedDeliveryDate;
	}
	public float getPricePerUnit() {
		return pricePerUnit;
	}
	public void setPricePerUnit(float pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	
}
