package com.nextech.erp.service;

import java.util.Date;
import java.util.List;
import com.nextech.erp.model.Productionplanning;

public interface ProductionplanningService extends CRUDService<Productionplanning>{
	
	public Productionplanning getProductionPlanningforCurrentMonthByProductIdAndDate(long pId,Date date)throws Exception;
	
	public List<Productionplanning> getProductionplanningByCurrentMonth(Date month) throws Exception;
	
	public List<Productionplanning> getProductionPlanByMonthYear(String month_year) throws Exception;
	
	public List<Productionplanning> updateProductionPlanByMonthYear(String month_year) throws Exception;

}
