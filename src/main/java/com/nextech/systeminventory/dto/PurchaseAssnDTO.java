package com.nextech.systeminventory.dto;




public class PurchaseAssnDTO extends AbstractDTO{

	private int quantity;
	private long purchaseId;
	private ProductDTO productId;
	
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
	
}
