package com.nextech.erp.model;

import java.io.Serializable;
import java.util.List;

public class ProductionPlan implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 360808637735345268L;
	private String month_year;
	private long product_id;
	private List<ProductProductionPlan> productProductionPlan;

	public String getMonth_year() {
		return month_year;
	}

	public void setMonth_year(String month_year) {
		this.month_year = month_year;
	}

	public long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}

	public List<ProductProductionPlan> getProductProductionPlan() {
		return productProductionPlan;
	}

	public void setProductProductionPlan(
			List<ProductProductionPlan> productProductionPlan) {
		this.productProductionPlan = productProductionPlan;
	}

}
