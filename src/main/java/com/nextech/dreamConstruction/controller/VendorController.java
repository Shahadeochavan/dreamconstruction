package com.nextech.dreamConstruction.controller;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
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

import com.nextech.dreamConstruction.constants.DreamConstructionConstants;
import com.nextech.dreamConstruction.dto.NotificationDTO;
import com.nextech.dreamConstruction.dto.VendorDTO;
import com.nextech.dreamConstruction.factory.MailResponseRequestFactory;
import com.nextech.dreamConstruction.factory.VendorFactory;
import com.nextech.dreamConstruction.model.Mail;
import com.nextech.dreamConstruction.model.Vendor;
import com.nextech.dreamConstruction.service.MailService;
import com.nextech.dreamConstruction.service.NotificationService;
import com.nextech.dreamConstruction.service.VendorService;
import com.nextech.dreamConstruction.status.UserStatus;

@Controller
@RequestMapping("/vendor")
public class VendorController {

	@Autowired
	VendorService vendorService;

	@Autowired
	private MessageSource messageSource;
    
	@Autowired
	static Logger logger = Logger.getLogger(VendorController.class);
	
	@Autowired
	MailService mailService;
	
	@Autowired
	NotificationService notificationService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addVendor(Model model,
			@Valid @RequestBody VendorDTO vendorDTO, BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				model.addAttribute("vendor", vendorDTO);
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}

			if (vendorService.getVendorByCompanyName(vendorDTO.getCompanyName()) != null) {
				return new UserStatus(2, messageSource.getMessage(DreamConstructionConstants.COMPANY_NAME_SHOULD_BE_UNIQUE, null, null));
			}
			if (vendorService.getVendorByEmail(vendorDTO.getEmail()) != null) {
				return new UserStatus(2,messageSource.getMessage(DreamConstructionConstants.EMAIL_SHOULD_BE_UNIQUE, null, null));
			}
			vendorService.addEntity(VendorFactory.setVendor(vendorDTO));
			NotificationDTO notificationDTO = notificationService.getNotificationByCode((messageSource.getMessage(DreamConstructionConstants.VENDOR_ADD_NOTIFICATION, null, null)));
			emailNotificationVendor(vendorDTO, notificationDTO);
			return new UserStatus(1, "vendor added Successfully !");
		} catch (ConstraintViolationException cve) {
			logger.error(cve);
			cve.printStackTrace();
			return new UserStatus(0, cve.getCause().getMessage());
		} catch (PersistenceException pe) {
			logger.error(pe);
			pe.printStackTrace();
			return new UserStatus(0, pe.getCause().getMessage());
		} catch (Exception e) {
			logger.error(e);
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
			logger.error(e);
			e.printStackTrace();
		}
		return vendor;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateVendor(@RequestBody VendorDTO vendorDTO,HttpServletRequest request,HttpServletResponse response) {
		try {
			VendorDTO oldVendorInfo = vendorService.getVendorById(vendorDTO.getId());
			
			if(!vendorDTO.getCompanyName().equals(oldVendorInfo.getCompanyName())){  	
				if (vendorService.getVendorByCompanyName(vendorDTO.getCompanyName()) != null) {
				return new UserStatus(2, messageSource.getMessage(DreamConstructionConstants.COMPANY_NAME_SHOULD_BE_UNIQUE, null, null));
				}
			 }
			
            if(!vendorDTO.getEmail().equals(oldVendorInfo.getEmail())){  	
				if (vendorService.getVendorByEmail(vendorDTO.getEmail()) != null) {
				return new UserStatus(2, messageSource.getMessage(DreamConstructionConstants.EMAIL_SHOULD_BE_UNIQUE, null, null));
				}
			 }
            vendorService.updateEntity( VendorFactory.setVendor(vendorDTO));
            NotificationDTO notificationDTO = notificationService.getNotificationByCode((messageSource.getMessage(DreamConstructionConstants.VENDOR_UPDATE_NOTIFICATION, null, null)));
			emailNotificationVendor(vendorDTO, notificationDTO);
			return new UserStatus(1, "Vendor update Successfully !");
		} catch (Exception e) {
			logger.error(e);
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
			logger.error(e);
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
			logger.error(e);
			return new UserStatus(0, e.toString());
		}
	}
	private void emailNotificationVendor(VendorDTO vendorDTO,NotificationDTO  notificationDTO) throws Exception{
		Mail mail = mailService.setMailCCBCCAndTO(notificationDTO);
		 String vendorEmail = mail.getMailTo()+","+vendorDTO.getEmail();
		   mail.setMailTo(vendorEmail);
		   mail.setModel(MailResponseRequestFactory.setMailDetailsVendor(vendorDTO));
		mailService.sendEmailWithoutPdF(mail, notificationDTO);
	}
}
