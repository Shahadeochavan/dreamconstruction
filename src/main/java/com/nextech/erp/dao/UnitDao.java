package com.nextech.erp.dao;

import java.util.List;

import com.nextech.erp.model.Unit;

public interface UnitDao {
	public Long addUnit(Unit unit) throws Exception;

	public Unit getUnitById(long id) throws Exception;

	public List<Unit> getUnitist() throws Exception;

	public boolean deleteUnit(long id) throws Exception;

	public Unit updateUnit(Unit unit) throws Exception;
}

