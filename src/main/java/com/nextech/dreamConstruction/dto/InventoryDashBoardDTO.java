package com.nextech.dreamConstruction.dto;

public class InventoryDashBoardDTO {

	private long productCount;
	private long productOrderCompleteCount;
	private long productOrderIncompleteCount;
	private long productOrderCompleteCountCurrentDate ;
	private long productOrderInCompleteCountCurrentDate;
	private long productOrderNewCount;

	public long getProductCount() {
		return productCount;
	}

	public void setProductCount(long productCount) {
		this.productCount = productCount;
	}

	public long getProductOrderCompleteCount() {
		return productOrderCompleteCount;
	}

	public void setProductOrderCompleteCount(long productOrderCompleteCount) {
		this.productOrderCompleteCount = productOrderCompleteCount;
	}

	public long getProductOrderIncompleteCount() {
		return productOrderIncompleteCount;
	}

	public void setProductOrderIncompleteCount(long productOrderIncompleteCount) {
		this.productOrderIncompleteCount = productOrderIncompleteCount;
	}

	public long getProductOrderCompleteCountCurrentDate() {
		return productOrderCompleteCountCurrentDate;
	}

	public void setProductOrderCompleteCountCurrentDate(
			long productOrderCompleteCountCurrentDate) {
		this.productOrderCompleteCountCurrentDate = productOrderCompleteCountCurrentDate;
	}

	public long getProductOrderInCompleteCountCurrentDate() {
		return productOrderInCompleteCountCurrentDate;
	}

	public void setProductOrderInCompleteCountCurrentDate(
			long productOrderInCompleteCountCurrentDate) {
		this.productOrderInCompleteCountCurrentDate = productOrderInCompleteCountCurrentDate;
	}

	public long getProductOrderNewCount() {
		return productOrderNewCount;
	}

	public void setProductOrderNewCount(long productOrderNewCount) {
		this.productOrderNewCount = productOrderNewCount;
	}
}
