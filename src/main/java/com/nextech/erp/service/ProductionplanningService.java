package com.nextech.erp.service;

import java.util.Date;

import com.nextech.erp.model.Productionplanning;

public interface ProductionplanningService extends CRUDService<Productionplanning>{
	
	public Productionplanning getProductionPlanningforCurrentMonthByProductIdAndDate(long pId,Date date)throws Exception;

}
