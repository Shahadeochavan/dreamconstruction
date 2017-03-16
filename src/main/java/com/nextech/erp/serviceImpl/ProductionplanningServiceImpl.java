package com.nextech.erp.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.ProductionplanningDao;
import com.nextech.erp.dto.ProductProductionPlan;
import com.nextech.erp.dto.ProductionPlan;
import com.nextech.erp.model.Product;
import com.nextech.erp.model.Productinventory;
import com.nextech.erp.model.Productionplanning;
import com.nextech.erp.model.Productorderassociation;
import com.nextech.erp.service.ProductService;
import com.nextech.erp.service.ProductinventoryService;
import com.nextech.erp.service.ProductionplanningService;
import com.nextech.erp.service.ProductorderService;
import com.nextech.erp.service.ProductorderassociationService;

public class ProductionplanningServiceImpl extends
		CRUDServiceImpl<Productionplanning> implements
		ProductionplanningService {
	
	@Autowired
	ProductionplanningDao productionplanningDao;

	@Autowired
	ProductinventoryService productinventoryService; 
	
	@Autowired
	ProductorderassociationService productorderassociationService; 
	
	@Autowired
	ProductorderService productorderService;
	
	@Autowired
	ProductService productService;
	
	@Override
	public Productionplanning getProductionPlanningforCurrentMonthByProductIdAndDate(
			long pId,Date date) throws Exception {
		// TODO Auto-generated method stub
		return productionplanningDao.getProductionPlanningforCurrentMonthByProductIdAndDate(pId,date);
	}

	@Override
	public List<Productionplanning> getProductionplanningByMonth(
			Date month) throws Exception {
		// TODO Auto-generated method stub
		return productionplanningDao.getProductionplanningByCurrentMonth(month);
	}
	@Override
	public Productionplanning getProductionplanByDateAndProductId(Date date,long pId) throws Exception {
		// TODO Auto-generated method stub
		return productionplanningDao.getProductionplanByDateAndProductId(date,pId);
	}
	@Override
	public List<ProductionPlan> getProductionPlanForCurrentMonth(
			) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.DAY_OF_MONTH, 1); 
//		int myMonth=cal.get(Calendar.MONTH);
		Date startDate = cal.getTime();
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date endDate = cal.getTime();
		List<Productionplanning> productionplanningList = productionplanningDao.getProductionPlanByMonthYear(startDate,endDate);
		return getProductionPlan(getProductProductPlanMap(productionplanningList), new SimpleDateFormat("MM-yyyy").format(new Date()));
		//return null;
	}

	private List<ProductionPlan> getProductionPlan(
			Map<Long, List<ProductProductionPlan>> productProductPlanMap, String month_year) throws Exception {
		Set<Entry<Long, List<ProductProductionPlan>>> productionPlanEntries = productProductPlanMap.entrySet();
		List<ProductionPlan> productionPlans = new ArrayList<>();
		for (Iterator<Entry<Long, List<ProductProductionPlan>>> iterator = productionPlanEntries.iterator(); iterator.hasNext();) {
			Entry<Long, List<ProductProductionPlan>> entry = (Entry<Long, List<ProductProductionPlan>>) iterator.next();
			Productinventory productinventory = productinventoryService.getProductinventoryByProductId(entry.getKey());
			List<Productorderassociation> productorderassociations = productorderassociationService.getProductorderassociationByProdcutId(entry.getKey());
			long remainingAmt = 0;
			if(productorderassociations != null){
				for (Productorderassociation productorderassociation : productorderassociations) {
					remainingAmt = remainingAmt+ productorderassociation.getRemainingQuantity();
				}
			}
			ProductionPlan productionPlan = new ProductionPlan();
			productionPlan.setProductId(entry.getKey());
		//	productionPlan.setProductBalanceQty(productinventory.getQuantityavailable());
			productionPlan.setProductTargetQty(remainingAmt);
			productionPlan.setProductProductionPlan(entry.getValue());
			productionPlan.setProductName(productService.getEntityById(Product.class, entry.getKey()).getPartNumber());
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

	@Override
	public List<Productionplanning> createProductionPlanMonthYear(
			List<Product> productList,String month_year) throws Exception {
		List<Productionplanning> productionPlanList = new ArrayList<Productionplanning>();
		Productionplanning productionplanning = null;
		Calendar cal = Calendar.getInstance();
		
		for (Iterator<Product> iterator = productList.iterator(); iterator.hasNext();) {
			Product product = (Product) iterator.next();
			cal.setTime(new Date());
			cal.set(Calendar.DAY_OF_MONTH, 1); 
			int myMonth=cal.get(Calendar.MONTH);

			while (myMonth==cal.get(Calendar.MONTH)) {
				productionplanning = new Productionplanning();
				productionplanning.setProduct(product);
				productionplanning.setIsactive(true);
				productionplanning.setDate(cal.getTime());
				productionplanningDao.add(productionplanning);
				productionPlanList.add(productionplanning);
			  System.out.print("product : " + product.getId() +" Date :" + new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime()));
			  cal.add(Calendar.DAY_OF_MONTH, 1);
			}
			
		}
		
		return productionPlanList;
	}

	@Override
	public void updateProductionplanningForCurrentMonth(
			List<ProductionPlan> productionplanningList) throws Exception {
		Calendar cal = Calendar.getInstance();
		for (Iterator<ProductionPlan> iterator = productionplanningList.iterator(); iterator
				.hasNext();) {
			ProductionPlan productionPlan = (ProductionPlan) iterator.next();
			List<ProductProductionPlan> productProductionPlans = productionPlan.getProductProductionPlan();
			for (Iterator<ProductProductionPlan> iterator2 = productProductionPlans.iterator(); iterator2
					.hasNext();) {
				ProductProductionPlan productProductionPlan = (ProductProductionPlan) iterator2
						.next();
				cal.setTime(productProductionPlan.getProductionDate());
				cal.set(Calendar.HOUR, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				Date productionDateStart = cal.getTime();
				cal.set(Calendar.HOUR, 23);
				cal.set(Calendar.MINUTE, 59);
				cal.set(Calendar.SECOND, 59);
				Date productionDateEnd = cal.getTime();
				Productionplanning productionplanning = productionplanningDao.getProductionPlanningByDateAndProductId(productionDateStart, productionDateEnd, productionPlan.getProductId());
				productionplanning.setAchivedQuantity(productProductionPlan.getAchived_quantity());
				productionplanning.setDispatchQuantity(productProductionPlan.getDispatch_quantity());
				productionplanning.setTargetQuantity(productProductionPlan.getTarget_quantity());
				productionplanningDao.update(productionplanning);
			}
			
			
		}
	}

	@Override
	public List<Productionplanning> getProductionplanByDate(Date date)
			throws Exception {
		return productionplanningDao.getProductionplanByDate(date);
	}
}
