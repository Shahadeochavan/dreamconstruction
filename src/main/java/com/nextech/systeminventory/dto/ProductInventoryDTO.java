package com.nextech.systeminventory.dto;

import java.util.List;


public class ProductInventoryDTO {
	
	private long orderId;
	List<ProductInvetoryMultipleData> productInvetoryMultipleDatas;
	
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public List<ProductInvetoryMultipleData> getProductInvetoryMultipleDatas() {
		return productInvetoryMultipleDatas;
	}
	public void setProductInvetoryMultipleDatas(
			List<ProductInvetoryMultipleData> productInvetoryMultipleDatas) {
		this.productInvetoryMultipleDatas = productInvetoryMultipleDatas;
	}
	

}
