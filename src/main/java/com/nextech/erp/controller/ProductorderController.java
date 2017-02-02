package com.nextech.erp.controller;

import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.nextech.erp.model.Productorderassociation;
import com.nextech.erp.model.ProductOrderAssociationModel;
import com.nextech.erp.model.Productorder;
import com.nextech.erp.service.ClientService;
import com.nextech.erp.service.ProductorderassociationService;
import com.nextech.erp.service.ProductorderService;
import com.nextech.erp.service.StatusService;
import com.nextech.erp.status.UserStatus;

@Controller
@RequestMapping("/productorder")
public class ProductorderController {

	@Autowired
	ProductorderService productorderService;
	
	@Autowired
	ProductorderassociationService productorderassociationService;  

	@Autowired
	ClientService clientService;
	
	@Autowired
	StatusService statusService; 
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addProductorder(
			@Valid @RequestBody Productorder productorder, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			productorderService.addProductorder(productorder);
			return new UserStatus(1, "Productorder added Successfully !");
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
	
	@RequestMapping(value = "/createmultiple", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addMultipleProductorder(
			@Valid @RequestBody ProductOrderAssociationModel productOrderAssociationModel, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError().getDefaultMessage());
			}
			Productorder productorder= new Productorder();
			productorder.setClient(clientService.getClientById(productOrderAssociationModel.getClient()));
			productorder.setCreateDate(new Date());
			productorder.setDescription(productOrderAssociationModel.getDescription());
			productorder.setExpecteddeliveryDate(productOrderAssociationModel.getDeliveryDate());
			productorder.setQuantity(productOrderAssociationModel.getOrderproductassociations().size());
			productorder.setStatus(statusService.getStatusById(2));
			productorder.setIsactive(true);
			Integer orderId = productorderService.addProductorder(productorder);
			List<Productorderassociation> productorderassociations = productOrderAssociationModel.getOrderproductassociations();
			if(productorderassociations !=null && !productorderassociations.isEmpty()){
				for (Productorderassociation productorderassociation : productorderassociations) {
					productorderassociation.setProductorder(productorder);
					productorderassociationService.addProductorderassociation(productorderassociation);
				}
			}
			return new UserStatus(1, "Multiple Productorder added Successfully !");
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
	public @ResponseBody Productorder getProductorder(@PathVariable("id") long id) {
		Productorder productorder = null;
		try {
			productorder = productorderService.getProductorderById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productorder;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateProductorder(@RequestBody Productorder productorder) {
		try {
			productorderService.updateProductorder(productorder);
			return new UserStatus(1, "Productorder update Successfully !");
		} catch (Exception e) {
			e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}

	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Productorder> getProductorder() {

		List<Productorder> productorderList = null;
		try {
			productorderList = productorderService.getProductorderList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return productorderList;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody UserStatus deleteProductorder(@PathVariable("id") long id) {

		try {
			Productorder productorder = productorderService.getProductorderById(id);
			productorder.setIsactive(false);
			productorderService.updateProductorder(productorder);
			return new UserStatus(1, "Productorder deleted Successfully !");
		} catch (Exception e) {
			return new UserStatus(0, e.toString());
		}

	}
}

