package com.nextech.erp.dto;

import java.sql.Date;
import java.util.List;

public class ProductionPlanCurrentDate {
	private Date createDate;
	private List<ProductinPlanCurrentDateList> productinPlanCurrentDateLists;
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public List<ProductinPlanCurrentDateList> getProductinPlanCurrentDateLists() {
		return productinPlanCurrentDateLists;
	}
	public void setProductinPlanCurrentDateLists(
			List<ProductinPlanCurrentDateList> productinPlanCurrentDateLists) {
		this.productinPlanCurrentDateLists = productinPlanCurrentDateLists;
	}
	
}
