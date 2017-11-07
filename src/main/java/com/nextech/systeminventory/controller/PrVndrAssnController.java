package com.nextech.systeminventory.controller;

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

import com.nextech.systeminventory.dto.PrVndrAssnDTO;
import com.nextech.systeminventory.factory.PrVndrAssnRequestResponseFactory;
import com.nextech.systeminventory.model.PrVndrAssn;
import com.nextech.systeminventory.status.UserStatus;
import com.nextech.systeminventory.service.PrVndrAssnService;


@Controller
@RequestMapping("/prVndrAssn")
public class PrVndrAssnController {

	@Autowired
	PrVndrAssnService PrVndrAssnService;

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addPrVndrAssn(@Valid @RequestBody PrVndrAssnDTO prVndrAssnDTO,
			BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			if(PrVndrAssnService.getPrVndrAssnByVendorIdProductId(prVndrAssnDTO.getVendor().getId(), prVndrAssnDTO.getProduct().getId())!=null){
			return new UserStatus(2,"Product vendor already exist");
			}
			PrVndrAssnService.addEntity(PrVndrAssnRequestResponseFactory.setPrVndrAssn(prVndrAssnDTO));
			return new UserStatus(1, "PrVndrAssn added Successfully !");
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
	public @ResponseBody PrVndrAssn getPrVndrAssn(@PathVariable("id") long id) {
		PrVndrAssn PrVndrAssn = null;
		try {
			PrVndrAssn = PrVndrAssnService.getEntityById(PrVndrAssn.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return PrVndrAssn;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateUserType(
			@RequestBody PrVndrAssn PrVndrAssn,HttpServletRequest request,HttpServletResponse response) {
		try {
			PrVndrAssn.setIsactive(true);
			PrVndrAssnService.updateEntity(PrVndrAssn);
			return new UserStatus(1, "PrVndrAssn update Successfully !");
		} catch (Exception e) {
			 e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<PrVndrAssn> getPrVndrAssn() {

		List<PrVndrAssn> PrVndrAssns = null;
		try {
			PrVndrAssns = PrVndrAssnService.getEntityList(PrVndrAssn.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return PrVndrAssns;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody UserStatus deletePrVndrAssn(@PathVariable("id") long id) {

		try {
			PrVndrAssn PrVndrAssn = PrVndrAssnService.getEntityById(PrVndrAssn.class,id);
			PrVndrAssn.setIsactive(false);
			PrVndrAssnService.updateEntity(PrVndrAssn);
			return new UserStatus(1, "PrVndrAssn deleted Successfully !");
		} catch (Exception e) {
			return new UserStatus(0, e.toString());
		}

	}
	
	@RequestMapping(value = "productList/{vendorId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody UserStatus getProductByVendorId(@PathVariable("vendorId") long vendorId) {
		List<PrVndrAssn> prVndrAssns =null;

		try {
			prVndrAssns = PrVndrAssnService.getPrVndrAssnByVendorId(vendorId);
			if(prVndrAssns.isEmpty()){
				return new UserStatus(1,"Please first you can do product vendor association");
			}
		} catch (Exception e) {
			return new UserStatus(0, e.toString());
		}
		return new UserStatus(1,prVndrAssns);

	}
}
