package com.nextech.erp.serviceImpl;

import java.util.Date;
import java.util.List;

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

	@Override
	public List<Productionplanning> getProductionplanningByCurrentMonth(
			Date month) throws Exception {
		// TODO Auto-generated method stub
		return productionplanningDao.getProductionplanningByCurrentMonth(month);
	}

	@Override
	public List<Productionplanning> getProductionPlanByMonthYear(
			String month_year) throws Exception {
		// TODO Auto-generated method stub
		return productionplanningDao.getProductionPlanByMonthYear(month_year);
	}

	@Override
	public List<Productionplanning> updateProductionPlanByMonthYear(
			String month_year) throws Exception {
		// TODO Auto-generated method stub
		return productionplanningDao.updateProductionPlanByMonthYear(month_year);
	}

}
