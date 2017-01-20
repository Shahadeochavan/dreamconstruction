package com.nextech.erp.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.UnitDao;
import com.nextech.erp.model.Unit;
import com.nextech.erp.service.UnitService;

public class UnitServiceImpl implements UnitService {
	@Autowired
	UnitDao unitdao;

	@Override
	public boolean addUnit(Unit unit) throws Exception {
		unit.setCreatedDate(new Timestamp(new Date().getTime()));
		return unitdao.addUnit(unit);
	}

	@Override
	public Unit getUnitById(long id) throws Exception {
		return unitdao.getUnitById(id);
	}

	@Override
	public List<Unit> getUnitist() throws Exception {
		return unitdao.getUnitist();
	}

	@Override
	public boolean deleteUnit(long id) throws Exception {
		return unitdao.deleteUnit(id);
	}

	@Override
	public Unit updateUnit(Unit unit) throws Exception {
		unit.setUpdatedDate(new Timestamp(new Date().getTime()));
		return unitdao.updateUnit(unit);
	}
}
