package com.nextech.dreamConstruction.dao;

import com.nextech.dreamConstruction.model.Unit;


public interface UnitDao extends SuperDao<Unit>{
	
	public Unit getUnitByName(String name)throws Exception;
}

