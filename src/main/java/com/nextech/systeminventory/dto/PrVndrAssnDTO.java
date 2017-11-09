package com.nextech.systeminventory.dto;


import com.nextech.systeminventory.model.Product;
import com.nextech.systeminventory.model.Vendor;

public class PrVndrAssnDTO extends AbstractDTO{

	private Vendor vendor;
	private Product product;
	private float pricePerUnit;

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
	
}
