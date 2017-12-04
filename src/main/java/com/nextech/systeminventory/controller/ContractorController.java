package com.nextech.systeminventory.controller;

import java.util.List;

import javax.naming.AuthenticationException;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nextech.systeminventory.dto.ContractorDTO;
import com.nextech.systeminventory.factory.ContractorRequestResponseFactory;
import com.nextech.systeminventory.model.Contractor;
import com.nextech.systeminventory.service.ContractorService;
import com.nextech.systeminventory.status.UserStatus;

@Controller
@RequestMapping("/contractor")
public class ContractorController {

	@Autowired
	ContractorService contractorService;
	
	@Autowired
	static Logger logger = Logger.getLogger(ContractorController.class);

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addContractor(@Valid @RequestBody ContractorDTO contractorDTO,
			BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			contractorService.addEntity(ContractorRequestResponseFactory.setContractor(contractorDTO, request));
			return new UserStatus(1, "Contractor added Successfully !");
		} catch (ConstraintViolationException cve) {
			logger.error(cve);
			cve.printStackTrace();
			return new UserStatus(0, cve.getCause().getMessage());
		} catch (PersistenceException pe) {
			logger.error(pe);
			pe.printStackTrace();
			return new UserStatus(0, pe.getCause().getMessage());
		} catch (AuthenticationException authException) {

			return new UserStatus(0, authException.getCause().getMessage());
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return new UserStatus(0, e.getCause().getMessage());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Contractor getContractor(@PathVariable("id") long id) {
		Contractor contractor = null;
		try {
			contractor = contractorService.getEntityById(Contractor.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contractor;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateContractor(
			@RequestBody ContractorDTO contractorDTO,HttpServletRequest request,HttpServletResponse response) {
		try {
			contractorService.updateEntity(ContractorRequestResponseFactory.setContractorUpdate(contractorDTO, request));
			return new UserStatus(1, "Contractor update Successfully !");
		} catch (Exception e) {
			 e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Contractor> getContractor() {

		List<Contractor> contractors = null;
		try {
			contractors = contractorService.getEntityList(Contractor.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return contractors;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody UserStatus deleteContractor(@PathVariable("id") long id) {

		try {
			Contractor contractor = contractorService.getEntityById(Contractor.class,id);
			contractor.setIsactive(false);
			contractorService.updateEntity(contractor);
			return new UserStatus(1, "Contractor deleted Successfully !");
		} catch (Exception e) {
			return new UserStatus(0, e.toString());
		}

	}
}
