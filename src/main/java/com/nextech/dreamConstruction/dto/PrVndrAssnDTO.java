package com.nextech.dreamConstruction.dto;


import java.util.List;

import com.nextech.dreamConstruction.model.Product;
import com.nextech.dreamConstruction.model.Vendor;

public class PrVndrAssnDTO extends AbstractDTO{

	private Vendor vendor;
	private Product product;
	private float pricePerUnit;
	private List<ProductAssoParts> productAssoParts;

	public PrVndrAssnDTO() {

	}

	public	PrVndrAssnDTO(int id) {
		this.setId(id);
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public float getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(float pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public List<ProductAssoParts> getProductAssoParts() {
		return productAssoParts;
	}

	public void setProductAssoParts(List<ProductAssoParts> productAssoParts) {
		this.productAssoParts = productAssoParts;
	}
	
}
