package com.nextech.systeminventory.dto;


import com.nextech.systeminventory.model.Product;
import com.nextech.systeminventory.model.Vendor;

public class PrVndrAssnDTO extends AbstractDTO{

	private Vendor vendor;
	private Product product;

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
}
