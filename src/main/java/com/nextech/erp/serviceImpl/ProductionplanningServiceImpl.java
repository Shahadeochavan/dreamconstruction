package com.nextech.erp.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.ProductionplanningDao;
import com.nextech.erp.model.Productionplanning;
import com.nextech.erp.service.ProductionplanningService;

public class ProductionplanningServiceImpl extends
		CRUDServiceImpl<Productionplanning> implements
		ProductionplanningService {
	
	@Autowired
	ProductionplanningDao productionplanningDao;

	@Override
	public Productionplanning getProductionPlanningforCurrentMonthByProductIdAndDate(
			long pId,Date date) throws Exception {
		// TODO Auto-generated method stub
		return productionplanningDao.getProductionPlanningforCurrentMonthByProductIdAndDate(pId,date);
	}

}
