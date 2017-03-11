package com.nextech.erp.dto;

import java.util.Date;



public class ProductProductionPlan {

	private Date productionDate;
	private int target_quantity;
	private int achived_quantity;
	private int dispatch_quantity;

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	public int getTarget_quantity() {
		return target_quantity;
	}

	public void setTarget_quantity(int target_quantity) {
		this.target_quantity = target_quantity;
	}

	public int getAchived_quantity() {
		return achived_quantity;
	}

	public void setAchived_quantity(int achived_quantity) {
		this.achived_quantity = achived_quantity;
	}

	public int getDispatch_quantity() {
		return dispatch_quantity;
	}

	public void setDispatch_quantity(int dispatch_quantity) {
		this.dispatch_quantity = dispatch_quantity;
	}

}
