package com.nextech.dreamConstruction.serviceImpl;

import java.util.ArrayList;
import java.util.List;








import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextech.dreamConstruction.dao.UnitDao;
import com.nextech.dreamConstruction.dto.UnitDTO;
import com.nextech.dreamConstruction.factory.UnitFactory;
import com.nextech.dreamConstruction.model.Unit;
import com.nextech.dreamConstruction.service.UnitService;

@Service
public class UnitServiceImpl extends CRUDServiceImpl<Unit> implements UnitService {
	
	@Autowired
	UnitDao unitDao;

	@Override
	public List<UnitDTO> getUnitList() throws Exception {
		
		List<UnitDTO> unitDTOs = new ArrayList<UnitDTO>();
		List<Unit> units = unitDao.getList(Unit.class);
		if(units.isEmpty()){
			return null;
		}
		for (Unit unit : units) {
			UnitDTO unitDTO = UnitFactory.setUnitDTO(unit);
			unitDTOs.add(unitDTO);
		}
		return unitDTOs;
	}

	@Override
	public UnitDTO getUnitByID(long id) throws Exception {
		
		Unit unit = unitDao.getById(Unit.class, id);
		if(unit==null){
			return null;
		}
		UnitDTO unitDTO = UnitFactory.setUnitDTO(unit);
		return unitDTO;
	}

	@Override
	public UnitDTO deleteUnit(long id) throws Exception {
		
		Unit unit = unitDao.getById(Unit.class, id);
		if(unit==null){
			return null;
		}
		unit.setIsactive(false);
		unitDao.update(unit);
		UnitDTO unitDTO = UnitFactory.setUnitDTO(unit);
		return unitDTO;
	}

	@Override
	public Unit getUnitByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return unitDao.getUnitByName(name);
	}

}
