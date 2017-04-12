package com.nextech.erp.dto;

public class ProductinPlanPRMAssoData {

	private String name;
	private long rawmaterial;
	private long quantityRequired;
	private long productionPlanId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getQuantityRequired() {
		return quantityRequired;
	}
	public void setQuantityRequired(long quantityRequired) {
		this.quantityRequired = quantityRequired;
	}
	public long getRawmaterial() {
		return rawmaterial;
	}
	public void setRawmaterial(long rawmaterial) {
		this.rawmaterial = rawmaterial;
	}
	public long getProductionPlanId() {
		return productionPlanId;
	}
	public void setProductionPlanId(long productionPlanId) {
		this.productionPlanId = productionPlanId;
	}


}
