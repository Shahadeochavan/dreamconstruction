package com.nextech.systeminventory.controller;

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

import com.nextech.systeminventory.constants.ERPConstants;
import com.nextech.systeminventory.dto.ClientDTO;
import com.nextech.systeminventory.dto.NotificationDTO;
import com.nextech.systeminventory.factory.ClientFactory;
import com.nextech.systeminventory.factory.MailResponseRequestFactory;
import com.nextech.systeminventory.model.Client;
import com.nextech.systeminventory.model.Mail;
import com.nextech.systeminventory.service.ClientService;
import com.nextech.systeminventory.service.MailService;
import com.nextech.systeminventory.service.NotificationService;
import com.nextech.systeminventory.service.ProductorderService;
import com.nextech.systeminventory.status.UserStatus;

@Controller
@RequestMapping("/client")
public class ClientController {

	@Autowired
	ClientService clientService;

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	ProductorderService productorderService;
	
	@Autowired
	static Logger logger = Logger.getLogger(ClientController.class);
	
	@Autowired
	MailService mailService;
	
	@Autowired
	NotificationService notificationService;

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addClient(
			@Valid @RequestBody ClientDTO clientDTO, BindingResult bindingResult,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			if (clientService.getClientByCompanyName(clientDTO.getCompanyName()) != null) {
				return new UserStatus(2, messageSource.getMessage(ERPConstants.COMPANY_NAME_SHOULD_BE_UNIQUE, null, null));
			}
			if (clientService.getClientByEmail(clientDTO.getEmailId()) != null) {
				return new UserStatus(2, messageSource.getMessage(ERPConstants.EMAIL_SHOULD_BE_UNIQUE, null, null));
			}
			clientService.addEntity(ClientFactory.setClient(clientDTO));
			NotificationDTO notificationDTO = notificationService.getNotificationByCode((messageSource.getMessage(ERPConstants.CLIENT_ADD_NOTIFICATION, null, null)));
			emailNotificationClient(clientDTO, request, response, notificationDTO);
			return new UserStatus(1, messageSource.getMessage(ERPConstants.CLIENT_ADDED, null, null));
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
	public @ResponseBody Client getClient(@PathVariable("id") long id) {
		Client client = null;
		try {
			client = clientService.getEntityById(Client.class, id);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return client;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateClient(@RequestBody ClientDTO clientDTO,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			ClientDTO oldClientInfo = clientService.getClientDTOById(clientDTO.getId());
			if(!clientDTO.getCompanyName().equals(oldClientInfo.getCompanyName())){  	
				if (clientService.getClientByCompanyName(clientDTO.getCompanyName()) != null) {
				return new UserStatus(2, messageSource.getMessage(ERPConstants.COMPANY_NAME_SHOULD_BE_UNIQUE, null, null));
				}
			 }
			
            if(!clientDTO.getEmailId().equals(oldClientInfo.getEmailId())){  			
				if (clientService.getClientByEmail(clientDTO.getEmailId()) != null) {
				return new UserStatus(2, messageSource.getMessage(ERPConstants.EMAIL_SHOULD_BE_UNIQUE, null, null));
				}
			 }
            
	    	clientService.updateEntity(ClientFactory.setClientUpdate(clientDTO));
	    	NotificationDTO notificationDTO = notificationService.getNotificationByCode((messageSource.getMessage(ERPConstants.CLIENT_UPDATE_NOTIFICATION, null, null)));
	    	emailNotificationClient(clientDTO, request, response, notificationDTO);
			return new UserStatus(1, messageSource.getMessage(ERPConstants.CLIENT_UPDATE, null, null));
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Client> getClient() {

		List<Client> clientList = null;
		try {
			clientList = clientService.getEntityList(Client.class);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return clientList;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody UserStatus deleteClient(@PathVariable("id") long id) {

		try {
			Client client = clientService.getEntityById(Client.class, id);
			client.setIsactive(false);
			clientService.updateEntity(client);
			return new UserStatus(1, messageSource.getMessage(
					ERPConstants.CLIENT_DELETE, null, null));
		} catch (Exception e) {
			logger.error(e);
			return new UserStatus(0, e.toString());
		}
	}
	private void emailNotificationClient(ClientDTO clientDTO,HttpServletRequest request, HttpServletResponse response,NotificationDTO notificationDTO) throws NumberFormatException,Exception {
		Mail mail = mailService.setMailCCBCCAndTO(notificationDTO);
		String mailSubject  = mailService.getSubject(notificationDTO);
		String clientEmailTo = mail.getMailTo() + "," + clientDTO.getEmailId();
		mail.setMailSubject(mailSubject);
		mail.setMailTo(clientEmailTo);
		mail.setModel(MailResponseRequestFactory.setMailDetailsClient(clientDTO));
		mailService.sendEmailWithoutPdF(mail, notificationDTO);
	}
}
