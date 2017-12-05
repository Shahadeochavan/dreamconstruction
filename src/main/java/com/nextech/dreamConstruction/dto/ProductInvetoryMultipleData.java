package com.nextech.dreamConstruction.dto;

import com.nextech.dreamConstruction.model.Product;

public class ProductInvetoryMultipleData extends AbstractDTO{
	
	private Product productId;
	private long quantity;
	
	
	public ProductInvetoryMultipleData(){
		
	}
    public ProductInvetoryMultipleData(int id){
		this.setId(id);
	}
	
	public Product getProductId() {
		return productId;
	}
	public void setProductId(Product productId) {
		this.productId = productId;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
}
