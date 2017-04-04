package com.nextech.erp.controller;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

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
import com.nextech.erp.dto.StoreOutDTO;
import com.nextech.erp.dto.StoreOutPart;
import com.nextech.erp.model.Storeout;
import com.nextech.erp.service.StoreoutService;
import com.nextech.erp.status.UserStatus;


@Controller
@RequestMapping("/storeout")
public class StoreoutController {
	
	@Autowired
	StoreoutService storeoutService;
	
	@Autowired
	private MessageSource messageSource;
	
	

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addStoreout(@Valid @RequestBody StoreOutDTO storeOutDTO,
			BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			for(StoreOutPart storeOutPart : storeOutDTO.getStoreOutParts()){
				Storeout storeout = setStoreParts(storeOutPart);
				storeout.setDescription(storeOutDTO.getDescription());
				storeout.setCreatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
				storeoutService.addEntity(storeout);
			}
			return new UserStatus(1, "Storeout added Successfully !");
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
	public @ResponseBody Storeout getStoreout(@PathVariable("id") long id) {
		Storeout Storeout = null;
		try {
			Storeout = storeoutService.getEntityById(Storeout.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Storeout;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateStoreout(
			@RequestBody Storeout Storeout,HttpServletRequest request,HttpServletResponse response) {
		try {
			Storeout.setIsactive(true);
			Storeout.setUpdatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
			storeoutService.updateEntity(Storeout);
			return new UserStatus(1, "Storeout update Successfully !");
		} catch (Exception e) {
			 e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Storeout> getStoreout() {

		List<Storeout> userList = null;
		try {
			userList = storeoutService.getEntityList(Storeout.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return userList;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody UserStatus deleteStoreout(@PathVariable("id") long id) {

		try {
			Storeout Storeout = storeoutService.getEntityById(Storeout.class,id);
			Storeout.setIsactive(false);
			storeoutService.updateEntity(Storeout);
			return new UserStatus(1, "Storeout deleted Successfully !");
		} catch (Exception e) {
			return new UserStatus(0, e.toString());
		}

	}
	
	private Storeout setStoreParts(StoreOutPart storeOutPart) throws Exception {
		Storeout storeout = new Storeout();
		storeout.setQuantityRequired(storeOutPart.getQuantityRequired());
		storeout.setQuantityDispatched(storeOutPart.getQuantityDispatched());
		storeout.setIsactive(true);
		return storeout;
	}
}

