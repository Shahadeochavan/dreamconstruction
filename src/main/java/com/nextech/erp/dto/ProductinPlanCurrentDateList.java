package com.nextech.erp.dto;

public class ProductinPlanCurrentDateList {
	private long productionPlanId;
	private long productId;
	private int targetQuantity;
	private int achivedQuantity;
	private String remark;

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public int getTargetQuantity() {
		return targetQuantity;
	}

	public void setTargetQuantity(int targetQuantity) {
		this.targetQuantity = targetQuantity;
	}

	public int getAchivedQuantity() {
		return achivedQuantity;
	}

	public void setAchivedQuantity(int achivedQuantity) {
		this.achivedQuantity = achivedQuantity;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public long getProductionPlanId() {
		return productionPlanId;
	}

	public void setProductionPlanId(long productionPlanId) {
		this.productionPlanId = productionPlanId;
	}

}
