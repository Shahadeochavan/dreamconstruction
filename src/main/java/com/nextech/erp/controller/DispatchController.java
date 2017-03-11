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
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nextech.erp.dto.DispatchDTO;
import com.nextech.erp.dto.Part;
import com.nextech.erp.model.Dispatch;
import com.nextech.erp.model.Product;
import com.nextech.erp.model.Productorder;
import com.nextech.erp.service.DispatchService;
import com.nextech.erp.service.ProductService;
import com.nextech.erp.service.ProductorderService;
import com.nextech.erp.status.UserStatus;

@Controller
@RequestMapping("/dispatch")
public class DispatchController {

	@Autowired
	DispatchService dispatchservice;
	
	@Autowired
	private MessageSource messageSource;

	@Autowired
	ProductorderService productorderService;
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addDispatch(@Valid @RequestBody Dispatch dispatch,
			BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError().getDefaultMessage());
			}
			dispatch.setIsactive(true);
			dispatchservice.addEntity(dispatch);
			return new UserStatus(1, "Dispatch added Successfully !");
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

	@RequestMapping(value = "/dispatchProducts", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus listDispatch(@RequestBody DispatchDTO dispatchDTO,
			BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError().getDefaultMessage());
			}
			for (Part part : dispatchDTO.getParts()) {
				Dispatch dispatch = setPart(part);
				dispatch.setProductorder(productorderService.getEntityById(Productorder.class, dispatchDTO.getOrderId()));
				dispatch.setInvoiceNo(dispatchDTO.getInvoiceNo());
				dispatchservice.addEntity(dispatch);
			}
			return new UserStatus(1, "Dispatch added Successfully !");
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
	
	private Dispatch setPart(Part part) throws Exception{
		Dispatch dispatch = new Dispatch();
		dispatch.setProduct(productService.getEntityById(Product.class, part.getProductId()));
		dispatch.setQuantity(part.getQuantity());
		dispatch.setIsactive(true);
		return dispatch;
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Dispatch getDispatch(@PathVariable("id") long id) {
		Dispatch dispatch = null;
		try {
			dispatch = dispatchservice.getEntityById(Dispatch.class,id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dispatch;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateDispatch(@RequestBody Dispatch dispatch) {
		try {
			dispatchservice.updateEntity(dispatch);
			return new UserStatus(1, "Dispatch update Successfully !");
		} catch (Exception e) {
			// e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Dispatch> getDispatch() {

		List<Dispatch> dispatchList = null;
		try {
			dispatchList = dispatchservice.getEntityList(Dispatch.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dispatchList;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody UserStatus deleteDispatch(@PathVariable("id") long id) {

		try {
			Dispatch dispatch = dispatchservice.getEntityById(Dispatch.class,id);
			dispatch.setIsactive(false);
			dispatchservice.updateEntity(dispatch);
			return new UserStatus(1, "Dispatch deleted Successfully !");
		} catch (Exception e) {
			return new UserStatus(0, e.toString());
		}

	}
}
