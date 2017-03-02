package com.nextech.erp.dao;

import java.util.Date;

import com.nextech.erp.model.Productionplanning;

public interface ProductionplanningDao extends SuperDao<Productionplanning> {
	public Productionplanning getProductionPlanningforCurrentMonthByProductIdAndDate(long pId,Date date)throws Exception;

}
