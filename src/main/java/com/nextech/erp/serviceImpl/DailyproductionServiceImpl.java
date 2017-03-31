package com.nextech.erp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.DailyproductionDao;
import com.nextech.erp.model.Dailyproduction;
import com.nextech.erp.service.DailyproductionService;

public class DailyproductionServiceImpl extends CRUDServiceImpl<Dailyproduction> implements DailyproductionService {

	@Autowired
	DailyproductionDao dailyproductionDao; 
	@Override
	public List<Dailyproduction> getDailyProdPendingForQualityCheckByPlanningId(long planningId) {
		return dailyproductionDao.getDailyProdPendingForQualityCheckByPlanningId(planningId);
	}

}
