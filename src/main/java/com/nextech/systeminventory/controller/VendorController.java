package com.nextech.systeminventory.controller;

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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.nextech.systeminventory.constants.ERPConstants;
import com.nextech.systeminventory.model.Vendor;
import com.nextech.systeminventory.service.VendorService;
import com.nextech.systeminventory.status.UserStatus;

@Controller
@RequestMapping("/vendor")
public class VendorController {

	@Autowired
	VendorService vendorService;

	@Autowired
	private MessageSource messageSource;


	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addVendor(Model model,
			@Valid @RequestBody Vendor vendor, BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				model.addAttribute("vendor", vendor);
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}

			if (vendorService.getVendorByCompanyName(vendor.getCompanyName()) == null) {

			} else {
				return new UserStatus(2, messageSource.getMessage(ERPConstants.COMPANY_NAME_EXIT, null, null));
			}
			if (vendorService.getVendorByEmail(vendor.getEmail()) == null) {
			} else {
				return new UserStatus(2,messageSource.getMessage(ERPConstants.EMAIL_ALREADY_EXIT, null, null));
			}
			//vendor.setCreatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
			vendor.setIsactive(true);
			vendorService.addEntity(vendor);
			return new UserStatus(1, "vendor added Successfully !");
		} catch (ConstraintViolationException cve) {
			cve.printStackTrace();
			return new UserStatus(0, cve.getCause().getMessage());
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			return new UserStatus(0, pe.getCause().getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new UserStatus(0, e.getCause().getMessage());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Vendor getVendor(@PathVariable("id") long id) {
		Vendor vendor = null;
		try {
			vendor = vendorService.getEntityById(Vendor.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vendor;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateVendor(@RequestBody Vendor vendor,HttpServletRequest request,HttpServletResponse response) {
		try {
			Vendor oldVendorInfo = vendorService.getEntityById(Vendor.class, vendor.getId());
			System.out.println(oldVendorInfo);
			if(vendor.getCompanyName().equals(oldVendorInfo.getCompanyName())){  	
			} else { 
				if (vendorService.getVendorByCompanyName(vendor.getCompanyName()) == null) {
			    }else{  
				return new UserStatus(2, messageSource.getMessage(ERPConstants.COMPANY_NAME_EXIT, null, null));
				}
			 }
            if(vendor.getEmail().equals(oldVendorInfo.getEmail())){  	
			} else { 
				if (vendorService.getVendorByEmail(vendor.getEmail()) == null) {
			    }else{  
				return new UserStatus(2, messageSource.getMessage(ERPConstants.EMAIL_ALREADY_EXIT, null, null));
				}
			 }
			vendor.setIsactive(true);
			vendorService.updateEntity(vendor);
			return new UserStatus(1, "Vendor update Successfully !");
		} catch (Exception e) {
			e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Vendor> getVendor() {

		List<Vendor> userList = null;
		try {
			userList = vendorService.getEntityList(Vendor.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody UserStatus deleteVendor(@PathVariable("id") long id) {

		try {
			Vendor vendor = vendorService.getEntityById(Vendor.class, id);
			vendor.setIsactive(false);
			vendorService.updateEntity(vendor);
			return new UserStatus(1, "Vendor deleted Successfully !");
		} catch (Exception e) {
			return new UserStatus(0, e.toString());
		}
	}
}
