package com.nextech.dreamConstruction.dto;


public class UnitDTO extends AbstractDTO{

	private String name;
	
	public UnitDTO(){
		
	}
	public UnitDTO(int id){
		this.setId(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
