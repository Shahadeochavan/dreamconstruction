package com.nextech.dreamConstruction.service;

import java.util.List;

import com.nextech.dreamConstruction.dto.UnitDTO;
import com.nextech.dreamConstruction.model.Unit;


public interface UnitService extends CRUDService<Unit>{
	
	public  List<UnitDTO> getUnitList() throws Exception;
	
	public UnitDTO getUnitByID(long id) throws Exception;
	
	public UnitDTO deleteUnit(long id) throws Exception;
	
	public Unit getUnitByName(String name)throws Exception;
	
}
