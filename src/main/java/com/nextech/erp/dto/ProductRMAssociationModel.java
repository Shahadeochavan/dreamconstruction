package com.nextech.erp.dto;

import java.util.List;

public class ProductRMAssociationModel {
	private long product;
	private List<ProductRMAssociationModelParts> productRMAssociationModelParts;
	
	
	public long getProduct() {
		return product;
	}
	public void setProduct(long product) {
		this.product = product;
	}
	public List<ProductRMAssociationModelParts> getProductRMAssociationModelParts() {
		return productRMAssociationModelParts;
	}
	public void setProductRMAssociationModelParts(
			List<ProductRMAssociationModelParts> productRMAssociationModelParts) {
		this.productRMAssociationModelParts = productRMAssociationModelParts;
	}

}
