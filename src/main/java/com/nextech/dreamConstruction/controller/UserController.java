package com.nextech.dreamConstruction.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.naming.AuthenticationException;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nextech.dreamConstruction.constants.DreamConstructionConstants;
import com.nextech.dreamConstruction.dto.NotificationDTO;
import com.nextech.dreamConstruction.dto.UserDTO;
import com.nextech.dreamConstruction.dto.UserTypePageAssoDTO;
import com.nextech.dreamConstruction.factory.MailResponseRequestFactory;
import com.nextech.dreamConstruction.factory.UserFactory;
import com.nextech.dreamConstruction.filter.TokenFactory;
import com.nextech.dreamConstruction.model.Authorization;
import com.nextech.dreamConstruction.model.Mail;
import com.nextech.dreamConstruction.model.Page;
import com.nextech.dreamConstruction.model.User;
import com.nextech.dreamConstruction.model.Usertype;
import com.nextech.dreamConstruction.service.MailService;
import com.nextech.dreamConstruction.service.NotificationService;
import com.nextech.dreamConstruction.service.UserService;
import com.nextech.dreamConstruction.service.UserTypeService;
import com.nextech.dreamConstruction.service.UsertypepageassociationService;
import com.nextech.dreamConstruction.status.UserStatus;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userservice;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	UserTypeService userTypeService;

	@Autowired
	UsertypepageassociationService usertypepageassociationService;
	
	@Autowired
	static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	MailService mailService;
	
	@Autowired
	NotificationService notificationService;

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addUser(@Valid @RequestBody UserDTO userDTO,
			BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}

				if (userservice.getUserByUserId(userDTO.getUserId()) != null) {
					return new UserStatus(2, messageSource.getMessage(DreamConstructionConstants.USER_ID_SHOULD_BE_UNIQUE, null, null));
				}
				if (userservice.getUserByEmail(userDTO.getEmailId()) != null) {
					return new UserStatus(2, messageSource.getMessage(DreamConstructionConstants.EMAIL_SHOULD_BE_UNIQUE, null, null));
				}
				if (userservice.getUserByMobile(userDTO.getMobileNo()) != null) {
					return new UserStatus(2, messageSource.getMessage(DreamConstructionConstants.CONTACT_NUMBER_SHOULD_BE_UNIQUE, null, null));
				}
			
				userservice.addEntity(UserFactory.setUser(userDTO, request));	
				
				//TODO sending the email for new user from admin
				NotificationDTO notificationDTO = notificationService.getNotificationByCode((messageSource.getMessage(DreamConstructionConstants.USER_ADD_NOTIFICATION, null, null)));
				emailNotificationUser(userDTO, request, response,notificationDTO);
				return new UserStatus(1, "User added Successfully !");
			
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

	@RequestMapping(value = "/login", method = RequestMethod.POST, headers = "Accept=application/json")
	public UserStatus login(@RequestBody User user, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		User user2 = userservice.getUserByUserId(user.getUserid());

		try {
			logger.error(messageSource.getMessage(DreamConstructionConstants.COUNT, null,
					null));
			if (user2 != null && authenticate(user, user2)) {
				Authorization authorization = new Authorization();
				authorization.setUserid(user.getUserid());
				authorization.setPassword(user.getPassword());
				authorization.setUpdatedDate(new Date());
				String token = TokenFactory.createAccessJwtToken(user2);
				authorization.setToken(token);
				response.addHeader("auth_token", token);
				Usertype usertype = userTypeService.getEntityById(
						Usertype.class, user2.getUsertype().getId());
				List<UserTypePageAssoDTO> usertypepageassociations = usertypepageassociationService
						.getPagesByUsertype(usertype.getId());
				HashMap<String, Object> result = new HashMap<String, Object>();
				List<Page> pages = new ArrayList<Page>();
				if (!usertypepageassociations.isEmpty()) {
					for (UserTypePageAssoDTO usertypepageassociation : usertypepageassociations) {
						Page pageDTO = new Page();
						pageDTO.setId(usertypepageassociation.getPage().getId());
						pageDTO.setMenu(usertypepageassociation.getPage().getMenu());
						pageDTO.setPageName(usertypepageassociation.getPage().getPageName());
						pageDTO.setSubmenu(usertypepageassociation.getPage().getSubmenu());
						pageDTO.setUrl(usertypepageassociation.getPage().getUrl());
						pages.add(pageDTO);
					}
					result.put("pages", pages);
				}
				String success = user.getUserid() + " logged in Successfully";
				return new UserStatus(1, success, result, user2);
			}
		} catch (AuthenticationException authException) {
			return new UserStatus(0, authException.getCause().getMessage());
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return new UserStatus(0, e.getCause().getMessage());
		}
		if (user2 == null) {
			return new UserStatus(0, "Please enetr correct userId");
		} else if (!user.getPassword().equals(user2.getPassword())) {
			return new UserStatus(0, "Please enetr correct password");
		}
		return new UserStatus(0, "Please enter correct credentials");

	}

	private boolean authenticate(User formUser, User dbUser) {
		if (formUser.getUserid().equals(dbUser.getUserid())
				&& formUser.getPassword().equals(dbUser.getPassword())) {
			dbUser.getFirstName();
			dbUser.getLastName();
			return true;
		} else {
			return false;
		}

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody User getUser(@PathVariable("id") long id) {
		User user = null;
		try {
			user = userservice.getEntityById(User.class, id);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return user;
	}

	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateUser(@RequestBody UserDTO userDTO,HttpServletRequest request,HttpServletResponse response) {
		try {
			User oldUserInfo = userservice.getEntityById(User.class, userDTO.getId());
			if(!userDTO.getUserId().equals(oldUserInfo.getUserid())){  
				if (userservice.getUserByUserId(userDTO.getUserId()) != null) {
				return new UserStatus(2, messageSource.getMessage(DreamConstructionConstants.USER_ID_SHOULD_BE_UNIQUE, null, null));
				}
			 }
            if(!userDTO.getEmailId().equals(oldUserInfo.getEmail())){  
				if (userservice.getUserByEmail(userDTO.getEmailId()) != null) {
				return new UserStatus(2, messageSource.getMessage(DreamConstructionConstants.EMAIL_SHOULD_BE_UNIQUE, null, null));
				}
			 }           
			if (!userDTO.getMobileNo().equals(oldUserInfo.getMobile())) {
				if (userservice.getUserByMobile(userDTO.getMobileNo()) != null) {
					return new UserStatus(2, messageSource.getMessage(DreamConstructionConstants.CONTACT_NUMBER_SHOULD_BE_UNIQUE, null, null));
				}
			}
			
			userservice.updateEntity(UserFactory.setUserUpdate(userDTO, request));
			NotificationDTO notificationDTO = notificationService.getNotificationByCode((messageSource.getMessage(DreamConstructionConstants.USER_UPDATE_NOTIFICATION, null, null)));
			emailNotificationUser(userDTO, request, response,notificationDTO);
			return new UserStatus(1, "User update Successfully !");
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}
	
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody UserStatus forgotPassWord(@RequestBody UserDTO userDTO,HttpServletRequest request,HttpServletResponse response) {
		try {
			UserDTO user2 = userservice.getUserByEmail(userDTO.getEmailId());
			if(user2==null){
				return new UserStatus(0,"Please enter registred email with dream construction");
			}
			NotificationDTO notificationDTO = notificationService.getNotificationByCode((messageSource.getMessage(DreamConstructionConstants.USER_FORGOT_NOTIFICATION, null, null)));
			emailNotificationUser(user2, request, response, notificationDTO);
			return new UserStatus(1, "Please check your email ! you have send password on your email");
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<User> getUser() {

		List<User> userList = null;
		try {
			userList = userservice.getEntityList(User.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}

	/* Delete an object from DB in Spring Restful Services */
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody UserStatus deleteEmployee(@PathVariable("id") long id) {

		try {
			User user = userservice.getEntityById(User.class, id);
			user.setIsactive(false);
			userservice.updateEntity(user);
			return new UserStatus(1, "User deleted Successfully !");
		} catch (Exception e) {
			return new UserStatus(0, e.toString());
		}

	}
	private void emailNotificationUser(UserDTO userDTO,HttpServletRequest request, HttpServletResponse response,NotificationDTO notificationDTO) throws NumberFormatException,Exception {
		Mail mail = mailService.setMailCCBCCAndTO(notificationDTO);
		String mailSubject  = mailService.getSubject(notificationDTO);
		String userEmailTo = mail.getMailTo() + "," + userDTO.getEmailId();
		mail.setMailSubject(mailSubject);
		mail.setMailTo(userEmailTo);
		mail.setModel(MailResponseRequestFactory.setMailDetailsUser(userDTO));
		mailService.sendEmailWithoutPdF(mail, notificationDTO);
	}
	
	@RequestMapping(value = "userInfo/{userId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody UserStatus getUserByUserId(@PathVariable("userId") String userId) {
		User user =null;

		try {
			user = userservice.getUserByUserId(userId);
		} catch (Exception e) {
			return new UserStatus(0, e.toString());
		}
		return new UserStatus(1,user);

	}
}
