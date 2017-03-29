package com.nextech.erp.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.nextech.erp.constants.ERPConstants;
import com.nextech.erp.dto.ProductinPlanCurrentDateList;
import com.nextech.erp.dto.ProductionPlan;
import com.nextech.erp.dto.ProductionPlanCurrentDate;
import com.nextech.erp.model.Product;
import com.nextech.erp.model.Productionplanning;
import com.nextech.erp.model.Productorderassociation;
import com.nextech.erp.service.ProductService;
import com.nextech.erp.service.ProductinventoryService;
import com.nextech.erp.service.ProductinventoryhistoryService;
import com.nextech.erp.service.ProductionplanningService;
import com.nextech.erp.service.ProductorderassociationService;
import com.nextech.erp.status.UserStatus;

@Controller
@RequestMapping("/productionplanning")
public class ProductionplanningController {


	@Autowired
	ProductionplanningService productionplanningService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	ProductinventoryService productinventoryService;
	
	@Autowired
	ProductinventoryhistoryService productinventoryhistoryService;
	
	@Autowired
	ProductorderassociationService productorderassociationService; 

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addProductionplanning(@Valid @RequestBody Productionplanning productionplanning,
			BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			
			productionplanningService.addEntity(productionplanning);
			return new UserStatus(1, "Productionplanning added Successfully !");
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
	@RequestMapping(value = "/createProductionForCurrentMonth", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus createProductionPlanningForCurrentMonth(@Valid @RequestBody Productionplanning productionplanning,
			BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			if (productionplanningService.getProductionPlanningforCurrentMonthByProductIdAndDate(
					productionplanning.getProduct().getId(),
					productionplanning.getDate())== null){
				productionplanningService.addEntity(productionplanning);
			}
			else{
				return new UserStatus(1,
						messageSource.getMessage(ERPConstants.PRODUCT_DATE_EXIT, null, null));
			}
			return new UserStatus(1,
					"Productrawmaterialassociation added Successfully !");
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

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Productionplanning getProductionplanning(@PathVariable("id") long id) {
		Productionplanning productionplanning = null;
		try {
			productionplanning = productionplanningService.getEntityById(Productionplanning.class,id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productionplanning;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateProductionplanning(@RequestBody ProductionPlanCurrentDate productionPlanCurrentDate, BindingResult bindingResult,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			for(ProductinPlanCurrentDateList productinPlanCurrentDateList : productionPlanCurrentDate.getProductinPlanCurrentDateLists()){
				Productionplanning productionplanning = setProductinPlanCurrentDate(productinPlanCurrentDateList);
						productionplanning.setUpdatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
						productionplanning.setDate(productionPlanCurrentDate.getCreateDate());
				productionplanning.setIsactive(true);
				productionplanningService.updateEntity(productionplanning);
				
			}
		
		} catch (Exception e) {
			 e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
		return new UserStatus(1, "Productionplanning update Successfully !");
	}
	

	private Productionplanning setProductinPlanCurrentDate(ProductinPlanCurrentDateList productinPlanCurrentDateList) throws Exception {
		Productionplanning productionplanning = new Productionplanning();
		productionplanning = productionplanningService.getEntityById(Productionplanning.class, productinPlanCurrentDateList.getProductionPlanId());
		productionplanning.setProduct(productService.getEntityById(Product.class, productinPlanCurrentDateList.getProductId()));
		productionplanning.setTargetQuantity(productinPlanCurrentDateList.getTargetQuantity());
		productionplanning.setAchivedQuantity(productinPlanCurrentDateList.getAchivedQuantity());
		productionplanning.setRemark(productinPlanCurrentDateList.getRemark());
		productionplanning.setIsactive(true);
		return productionplanning;
	}
	@RequestMapping(value = "/updateProductionPlan", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateProductionplanningForCurrentMonth(@RequestBody List<ProductionPlan> productionplanningList,HttpServletRequest request,HttpServletResponse response) {
		try {
			productionplanningService.updateProductionplanningForCurrentMonth(productionplanningList, request, response);
			return new UserStatus(1, "Productionplanning update Successfully !");
		} catch (Exception e) {
			 e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Productionplanning> getProductionplanning() {

		List<Productionplanning> productionplanningList = null;
		try {
			productionplanningList = productionplanningService.getEntityList(Productionplanning.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return productionplanningList;
	}
	
	@RequestMapping(value = "getProductionplanningByMonth/{month}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Productionplanning> getProductionplanningByMonth(@PathVariable("month") Date month) {

		List<Productionplanning> productionplanningList = null;
		try {
		//	productionplanningList = productionplanningService.getEntityList(Productionplanning.class);
			productionplanningList = productionplanningService.getProductionplanningByMonth(month);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return productionplanningList;
	}
	
	@RequestMapping(value = "getProductionPlanForCurrentMonth", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<ProductionPlan> getProductionPlanMonthYear() {

		List<ProductionPlan> productionplanningList = null;
		try {
			productionplanningList = productionplanningService.getProductionPlanForCurrentMonth();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return productionplanningList;
	}
	@RequestMapping(value = "updateProductionPlanMonthYear/{MONTH-YEAR}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody List<Productionplanning> updateProductionPlanMonthYear(@PathVariable("MONTH-YEAR") String month_year) {

		List<Productionplanning> productionplanningList = null;
		try {
			
			productionplanningList = productionplanningService.updateProductionPlanByMonthYear(month_year);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return productionplanningList;
	}
	
	@RequestMapping(value = "createProductionPlanMonthYear/{MONTH-YEAR}", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody List<Productionplanning> createProductionPlanMonthYear(@PathVariable("MONTH-YEAR") String month_year,HttpServletRequest request,HttpServletResponse response) {

		List<Productionplanning> productionplanningList = null;
		List<Product> productList = null;
		try {
			productList = productService.getEntityList(Product.class);
			productionplanningList = productionplanningService.createProductionPlanMonthYear( productList, month_year, request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return productionplanningList;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody UserStatus deleteProductionplanning(@PathVariable("id") long id) {

		try {
			Productionplanning productionplanning = productionplanningService.getEntityById(Productionplanning.class,id);
			productionplanning.setIsactive(false);
			productionplanningService.updateEntity(productionplanning);
			return new UserStatus(1, "Productionplanning deleted Successfully !");
		} catch (Exception e) {
			return new UserStatus(0, e.toString());
		}

	}
	
	@RequestMapping(value = "getProductionPlanByDateAndPId/{date}/{pID}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Productionplanning getProductionPlanDateAndProductId(@PathVariable("date") Date date,@PathVariable("pID")long pId) {

		Productionplanning productionplanning = null;
		try {
			productionplanning = productionplanningService.getProductionplanByDateAndProductId(date,pId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return productionplanning;
	}
	@RequestMapping(value = "getProductionPlanListByDate/{date}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Productionplanning> getProductionPlanDate1(@PathVariable("date") Date date) {

		List<Productionplanning> productionplanningsList = null;
		try {
			productionplanningsList = productionplanningService.getProductionplanByDate(date);
					
		} catch (Exception e) {
			e.printStackTrace();
		}

		return productionplanningsList;
	}
	
	
	@RequestMapping(value = "getProductionPlanByDate/{date}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Productionplanning> getProductionPlanDate(@PathVariable("date") Date date) {

		List<Productionplanning> productionplanningFinalList = new ArrayList<Productionplanning>();
		try {
		
			List<Productionplanning> productionplanningList = productionplanningService.getProductionplanByDate(date);
			for (Productionplanning productionplanning : productionplanningList) {
				boolean isProductRemaining = false;
				List<Productorderassociation> productorderassociations = productorderassociationService.getIncompleteProductOrderAssoByProdutId(productionplanning.getProduct().getId());
				if(productorderassociations !=null && !productorderassociations.isEmpty()){
					for (Productorderassociation productorderassociation : productorderassociations) {
						if(productorderassociation.getRemainingQuantity() > 0){
							isProductRemaining = true;
							break;
						}
					}
				}
				if(isProductRemaining)
					productionplanningFinalList.add(productionplanning);
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}

		return productionplanningFinalList;
	}
	
	
}

