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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nextech.dreamConstruction.dto.PageDTO;
import com.nextech.dreamConstruction.dto.UserTypePageAssoDTO;
import com.nextech.dreamConstruction.dto.UserTypePageAssoPart;
import com.nextech.dreamConstruction.model.Usertypepageassociation;
import com.nextech.dreamConstruction.service.PageService;
import com.nextech.dreamConstruction.service.UsertypepageassociationService;
import com.nextech.dreamConstruction.status.UserStatus;

@Controller
@RequestMapping("/usertypepageassociation")
public class UsertypepageassociationController {

	@Autowired
	UsertypepageassociationService usertypepageassociationService;

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	PageService pageservice;
	
	@Autowired
	static Logger logger = Logger.getLogger(UsertypepageassociationController.class);

	@RequestMapping(value = "/createMultiple", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addMultipleUserTypePageAsso(
			@Valid @RequestBody UserTypePageAssoDTO userTypePageAssoDTO,
			BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			List<UserTypePageAssoPart> userTypePageAssoParts =	userTypePageAssoDTO.getUserTypePageAssoParts();
			if(!userTypePageAssoParts.isEmpty()){
			for (UserTypePageAssoPart userTypePageAssoPart : userTypePageAssoParts) {	
			if (usertypepageassociationService.getUserTypePageAssoByPageIduserTypeId((userTypePageAssoPart.getPageId().getId()),userTypePageAssoDTO.getUsertypeId().getId()) == null){
				usertypepageassociationService.addMultipleUserTypePageAsso(userTypePageAssoDTO, request.getAttribute("current_user").toString());
			}/*else{
				PageDTO pageDTO = pageservice.getPageDTOById(userTypePageAssoPart.getPageId().getId());
				String pageName = pageDTO.getPageName()+" already exists";
				return new UserStatus(2, pageName);
			}*/
			}
			}else{
				return new UserStatus(2,"Please select page and click on add button");
			}
			return new UserStatus(1,"User Type Page Association added uccessfully !");
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
	public @ResponseBody Usertypepageassociation getPageAss(
			@PathVariable("id") long id) {
		Usertypepageassociation usertypepageassociation = null;
		try {
			usertypepageassociation = usertypepageassociationService
					.getEntityById(Usertypepageassociation.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usertypepageassociation;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updatePageAss(
			@RequestBody Usertypepageassociation usertypepageassociation,HttpServletRequest request,HttpServletResponse response) {
		try {
			usertypepageassociation.setIsactive(true);
			usertypepageassociationService.updateEntity(usertypepageassociation);
			return new UserStatus(1,"Usertypepageassociation update Successfully !");
		} catch (Exception e) {
			e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Usertypepageassociation> getPageAss() {

		List<Usertypepageassociation> UsertypepageassociationList = null;
		try {
			UsertypepageassociationList = usertypepageassociationService.getEntityList(Usertypepageassociation.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return UsertypepageassociationList;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody UserStatus deletePageAss(@PathVariable("id") long id) {

		try {
			Usertypepageassociation usertypepageassociation = usertypepageassociationService.getEntityById(Usertypepageassociation.class, id);
			usertypepageassociation.setIsactive(false);
			usertypepageassociationService.updateEntity(usertypepageassociation);
			return new UserStatus(1,"Usertypepageassociation deleted Successfully !");
		} catch (Exception e) {
			e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}
}
