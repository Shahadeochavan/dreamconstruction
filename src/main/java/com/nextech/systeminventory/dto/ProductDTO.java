package com.nextech.systeminventory.dto;


public class ProductDTO  extends AbstractDTO{

	private String clientPartNumber;
	private String design;
	private String name;
	private String partNumber;
	private String pricePerUnit;
	
	public ProductDTO(){
		
	}
	public ProductDTO(int id){
		this.setId(id);
	}
	public String getClientPartNumber() {
		return clientPartNumber;
	}
	public void setClientPartNumber(String clientPartNumber) {
		this.clientPartNumber = clientPartNumber;
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
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public String getPricePerUnit() {
		return pricePerUnit;
	}
	public void setPricePerUnit(String pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}


}
