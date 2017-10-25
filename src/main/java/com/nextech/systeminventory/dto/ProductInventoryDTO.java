package com.nextech.systeminventory.dto;

import java.util.List;


public class ProductInventoryDTO extends AbstractDTO{
	
	private long orderId;
	private String productPartNumber;
	private long inventoryQuantity;
	private long minimumQuantity;
	private long maximumQuantity;
	private String name;
	private long quantityAvailable;
	private long rackNumber;
	private long productId;
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
	public String getProductPartNumber() {
		return productPartNumber;
	}
	public void setProductPartNumber(String productPartNumber) {
		this.productPartNumber = productPartNumber;
	}
	public long getInventoryQuantity() {
		return inventoryQuantity;
	}
	public void setInventoryQuantity(long inventoryQuantity) {
		this.inventoryQuantity = inventoryQuantity;
	}
	public long getMinimumQuantity() {
		return minimumQuantity;
	}
	public void setMinimumQuantity(long minimumQuantity) {
		this.minimumQuantity = minimumQuantity;
	}
	public long getMaximumQuantity() {
		return maximumQuantity;
	}
	public void setMaximumQuantity(long maximumQuantity) {
		this.maximumQuantity = maximumQuantity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getQuantityAvailable() {
		return quantityAvailable;
	}
	public void setQuantityAvailable(long quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}
	public long getRackNumber() {
		return rackNumber;
	}
	public void setRackNumber(long rackNumber) {
		this.rackNumber = rackNumber;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
}
