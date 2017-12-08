package com.nextech.dreamConstruction.dto;

import com.nextech.dreamConstruction.model.Unit;


public class ProductDTO  extends AbstractDTO{

	private String design;
	private String name;
    private String productCode;
	private String hsnCode;
	private String pricePerUnit;
	private double gst;
	private Unit unit;
	
	
	public ProductDTO(){
		
	}
	public ProductDTO(int id){
		this.setId(id);
	}

	public String getDesign() {
		return design;
	}
	public void setDesign(String design) {
		this.design = design;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getHsnCode() {
		return hsnCode;
	}
	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}
	public String getPricePerUnit() {
		return pricePerUnit;
	}
	public void setPricePerUnit(String pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	public double getGst() {
		return gst;
	}
	public void setGst(double gst) {
		this.gst = gst;
	}
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}

}
