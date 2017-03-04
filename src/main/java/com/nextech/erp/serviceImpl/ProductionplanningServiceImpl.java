package com.nextech.erp.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.ProductionplanningDao;
import com.nextech.erp.model.ProductProductionPlan;
import com.nextech.erp.model.ProductionPlan;
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
	public List<ProductionPlan> getProductionPlanByMonthYear(
			String month_year) throws Exception {
		List<Productionplanning> productionplanningList = productionplanningDao.getProductionPlanByMonthYear(month_year);
		return getProductionPlan(getProductProductPlanMap(productionplanningList), month_year);
		//return null;
	}

	private List<ProductionPlan> getProductionPlan(
			Map<Long, List<ProductProductionPlan>> productProductPlanMap, String month_year) {
		Set<Entry<Long, List<ProductProductionPlan>>> productionPlanEntries = productProductPlanMap.entrySet();
		List<ProductionPlan> productionPlans = new ArrayList<>();
		for (Iterator<Entry<Long, List<ProductProductionPlan>>> iterator = productionPlanEntries.iterator(); iterator
				.hasNext();) {
			Entry<Long, List<ProductProductionPlan>> entry = (Entry<Long, List<ProductProductionPlan>>) iterator
					.next();
			ProductionPlan productionPlan = new ProductionPlan();
			productionPlan.setProduct_id(entry.getKey());
			productionPlan.setProductProductionPlan(entry.getValue());
			productionPlan.setMonth_year(month_year);
			productionPlans.add(productionPlan);
		}
		return productionPlans;
	}

	private Map<Long,List<ProductProductionPlan>> getProductProductPlanMap(
			List<Productionplanning> productionplanningList) {
		Map<Long,List<ProductProductionPlan>> productionPlanMap = new HashMap<Long, List<ProductProductionPlan>>();
		for (Iterator<Productionplanning> iterator = productionplanningList.iterator(); iterator
				.hasNext();) {
			Productionplanning productionplanning = (Productionplanning) iterator
					.next();
			List<ProductProductionPlan> productProductionPlans = null;
			if(productionPlanMap.get(productionplanning.getProduct().getId()) == null){
				productProductionPlans = new ArrayList<ProductProductionPlan>();
			}else{
				productProductionPlans = productionPlanMap.get(productionplanning.getProduct().getId());
			}
			productProductionPlans.add(getProductProductionPlanBean(productionplanning));
			productionPlanMap.put(productionplanning.getProduct().getId(), productProductionPlans);			
		}		
		return productionPlanMap;
	}

	private ProductProductionPlan getProductProductionPlanBean(
			Productionplanning productionplanning) {
		ProductProductionPlan productProductionPlan = new ProductProductionPlan();
		productProductionPlan.setAchived_quantity(productionplanning.getAchivedQuantity());
		productProductionPlan.setDispatch_quantity(productionplanning.getDispatchQuantity());
		productProductionPlan.setTarget_quantity(productionplanning.getTargetQuantity());
		productProductionPlan.setProductionDate(productionplanning.getDate());
		return productProductionPlan;
	}

	@Override
	public List<Productionplanning> updateProductionPlanByMonthYear(
			String month_year) throws Exception {
		// TODO Auto-generated method stub
		return productionplanningDao.updateProductionPlanByMonthYear(month_year);
	}

}
