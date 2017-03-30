package com.nextech.erp.controller;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nextech.erp.constants.ERPConstants;
import com.nextech.erp.dto.ProductinPlanCurrentDateList;
import com.nextech.erp.dto.ProductionPlanCurrentDate;
import com.nextech.erp.model.Dailyproduction;
import com.nextech.erp.model.Productionplanning;
import com.nextech.erp.model.Status;
import com.nextech.erp.service.DailyproductionService;
import com.nextech.erp.service.ProductService;
import com.nextech.erp.service.ProductionplanningService;
import com.nextech.erp.service.StatusService;
import com.nextech.erp.status.UserStatus;

@RestController
@RequestMapping("/dailyproduction")
public class DailyproductionController {
	@Autowired
	DailyproductionService dailyproductionservice;
	
	@Autowired 
	ProductService productService;
	@Autowired
	StatusService statusService;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	ProductionplanningService productionplanningService;

	@RequestMapping(value = "/dailyproductionSave", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addDailyproduction(@Valid @RequestBody ProductionPlanCurrentDate productionPlanCurrentDate,HttpServletRequest request,HttpServletResponse response,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			for(ProductinPlanCurrentDateList productinPlanCurrentDateList : productionPlanCurrentDate.getProductinPlanCurrentDateLists()){
				Dailyproduction dailyproduction = setProductinPlanCurrentDate(productinPlanCurrentDateList);
				dailyproduction.setIsactive(true);	
				dailyproduction.setStatus(statusService.getEntityById(Status.class, Long.parseLong(messageSource.getMessage(ERPConstants.STATUS_QUALITY_CHECK_PENDING, null, null))));
		       	dailyproductionservice.addEntity(dailyproduction);
	/*	Productionplanning productionplanning = productionplanningService.getEntityById(Productionplanning.class, dailyproduction.getProductionplanning().getId());
			productionplanning.setTargetQuantity(dailyproduction.getTargetQuantity());
			productionplanning.setAchivedQuantity(productionplanning.getAchivedQuantity()+dailyproduction.getAchivedQuantity());
			productionplanning.setDate(productionPlanCurrentDate.getCreateDate());
			productionplanning.setUpdatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
			productionplanningService.updateEntity(productionplanning);
			updateDailyproduction(dailyproduction, request, response);*/
		       	//TODO update production plan daily
		       	updateProductionPlan(dailyproduction, productionPlanCurrentDate, request, response);
			
			}
			
			return new UserStatus(1, "Dailyproduction added Successfully !");
		} catch (ConstraintViolationException cve) {
			System.out.println("Inside ConstraintViolationException");
			cve.printStackTrace();
			return new UserStatus(0, cve.getCause().getMessage());
		} catch (PersistenceException pe) {
			System.out.println("Inside PersistenceException");
			pe.printStackTrace();
			return new UserStatus(0, pe.getCause().getMessage());
		} catch (Exception e) {
			System.out.println("Inside Exception");
			e.printStackTrace();
			return new UserStatus(0, e.getCause().getMessage());
		}
	}

	private Dailyproduction setProductinPlanCurrentDate(ProductinPlanCurrentDateList productinPlanCurrentDateList) throws Exception {
		Dailyproduction dailyproduction = new Dailyproduction();
		dailyproduction.setProductionplanning(productionplanningService.getEntityById(Productionplanning.class, productinPlanCurrentDateList.getProductionPlanId()));
		dailyproduction.setTargetQuantity(productinPlanCurrentDateList.getTargetQuantity());
		dailyproduction.setAchivedQuantity(productinPlanCurrentDateList.getAchivedQuantity());
		dailyproduction.setRemark(productinPlanCurrentDateList.getRemark());
		dailyproduction.setIsactive(true);
		return dailyproduction;
	}
	private void updateProductionPlan(Dailyproduction dailyproduction,ProductionPlanCurrentDate productionPlanCurrentDate,HttpServletRequest request, HttpServletResponse response) throws Exception{
		Productionplanning productionplanning = productionplanningService.getEntityById(Productionplanning.class, dailyproduction.getProductionplanning().getId());
		productionplanning.setTargetQuantity(dailyproduction.getTargetQuantity());
		productionplanning.setAchivedQuantity(productionplanning.getAchivedQuantity()+dailyproduction.getAchivedQuantity());
		productionplanning.setDate(productionPlanCurrentDate.getCreateDate());
		productionplanning.setUpdatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
		productionplanning.setStatus(statusService.getEntityById(Status.class, Long.parseLong(messageSource.getMessage(ERPConstants.STATUS_QUALITY_CHECK_INPROGRESS, null, null))));
		productionplanningService.updateEntity(productionplanning);
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Dailyproduction getDailyproduction(@PathVariable("id") long id) {
		Dailyproduction Dailyproduction = null;
		try {
			Dailyproduction = dailyproductionservice.getEntityById(Dailyproduction.class,id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Dailyproduction;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateDailyproduction(@RequestBody Dailyproduction Dailyproduction,HttpServletRequest request,HttpServletResponse response) {
		try {
			Dailyproduction.setUpdatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
			Dailyproduction.setIsactive(true);
			dailyproductionservice.updateEntity(Dailyproduction);
			return new UserStatus(1, "Dailyproduction update Successfully !");
		} catch (Exception e) {
			 e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Dailyproduction> getDailyproduction() {

		List<Dailyproduction> DailyproductionList = null;
		try {
			DailyproductionList = dailyproductionservice.getEntityList(Dailyproduction.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return DailyproductionList;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody UserStatus deleteDailyproduction(@PathVariable("id") long id) {

		try {
			Dailyproduction Dailyproduction = dailyproductionservice.getEntityById(Dailyproduction.class, id);
			Dailyproduction.setIsactive(false);
			dailyproductionservice.updateEntity(Dailyproduction);
			return new UserStatus(1, "Dailyproduction deleted Successfully !");
		} catch (Exception e) {
			return new UserStatus(0, e.toString());
		}

	}
}

