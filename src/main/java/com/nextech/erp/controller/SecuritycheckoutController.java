package com.nextech.erp.controller;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nextech.erp.model.Securitycheckout;
import com.nextech.erp.service.ProductorderService;
import com.nextech.erp.service.ProductorderassociationService;
import com.nextech.erp.service.SecuritycheckoutService;
import com.nextech.erp.status.UserStatus;


@Controller
@RequestMapping("/securitycheckout")
public class SecuritycheckoutController {
	
	@Autowired
	SecuritycheckoutService securitycheckoutService;
	
	@Autowired
	ProductorderService productorderService;
	
	
	@Autowired
	ProductorderassociationService productorderassociationService;
	
	
	

	@RequestMapping(value = "/securityCheckOut", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addSecuritycheckout(@Valid @RequestBody Securitycheckout securitycheckout,
			BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			securitycheckout.setIsactive(true);
			securitycheckout.setCreatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
			securitycheckoutService.addEntity(securitycheckout);
			
			return new UserStatus(1, "Securitycheckout added Successfully !");
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
	public @ResponseBody Securitycheckout getSecuritycheckout(@PathVariable("id") long id) {
		Securitycheckout securitycheckout = null;
		try {
			securitycheckout = securitycheckoutService.getEntityById(Securitycheckout.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return securitycheckout;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateSecuritycheckout(
			@RequestBody Securitycheckout securitycheckout,HttpServletRequest request,HttpServletResponse response) {
		try {
			securitycheckout.setIsactive(true);
			securitycheckout.setUpdatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
			securitycheckoutService.updateEntity(securitycheckout);
			return new UserStatus(1, "Securitycheckout update Successfully !");
		} catch (Exception e) {
			 e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Securitycheckout> getSecuritycheckout() {

		List<Securitycheckout> securitycheckoutList = null;
		try {
			securitycheckoutList = securitycheckoutService.getEntityList(Securitycheckout.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return securitycheckoutList;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody UserStatus deleteSecuritycheckout(@PathVariable("id") long id) {

		try {
			Securitycheckout securitycheckout = securitycheckoutService.getEntityById(Securitycheckout.class,id);
			securitycheckout.setIsactive(false);
			securitycheckoutService.updateEntity(securitycheckout);
			return new UserStatus(1, "Securitycheckout deleted Successfully !");
		} catch (Exception e) {
			return new UserStatus(0, e.toString());
		}

	}

}

