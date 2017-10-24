package com.nextech.systeminventory.dto;


public class ProductOrderAssociationDTO extends AbstractDTO {

	private long quantity;
	private long remainingQuantity;
	private long productId;
	private long productOrderId;
	
	public ProductOrderAssociationDTO(){
		
	}
    public ProductOrderAssociationDTO(int id){
	this.setId(id);	
	}
	
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public long getRemainingQuantity() {
		return remainingQuantity;
	}
	public void setRemainingQuantity(long remainingQuantity) {
		this.remainingQuantity = remainingQuantity;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public long getProductOrderId() {
		return productOrderId;
	}
	public void setProductOrderId(long productOrderId) {
		this.productOrderId = productOrderId;
	}
}
