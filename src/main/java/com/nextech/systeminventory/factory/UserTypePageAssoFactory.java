package com.nextech.systeminventory.factory;

import javax.servlet.http.HttpServletRequest;

import com.nextech.systeminventory.dto.PageDTO;
import com.nextech.systeminventory.dto.UserTypeDTO;
import com.nextech.systeminventory.dto.UserTypePageAssoDTO;
import com.nextech.systeminventory.model.Usertype;
import com.nextech.systeminventory.model.Usertypepageassociation;


public class UserTypePageAssoFactory {

	public static  Usertypepageassociation setUserTypePageAss(UserTypePageAssoDTO userTypePageAssoDTO){
		Usertypepageassociation usertypepageassociation = new Usertypepageassociation();
		usertypepageassociation.setId(userTypePageAssoDTO.getId());
		Usertype usertype = new Usertype();
		usertype.setId(userTypePageAssoDTO.getUsertypeId().getId());
		usertypepageassociation.setUsertype(usertype);
		usertypepageassociation.setIsactive(true);
		return usertypepageassociation;
	}
	public static  Usertypepageassociation setUserTypePageAssUpdate(UserTypePageAssoDTO userTypePageAssoDTO, HttpServletRequest request){
		Usertypepageassociation usertypepageassociation = new Usertypepageassociation();
		usertypepageassociation.setId(userTypePageAssoDTO.getId());
		Usertype usertype = new Usertype();
		usertype.setId(userTypePageAssoDTO.getUsertypeId().getId());
		usertypepageassociation.setIsactive(true);
		usertypepageassociation.setUpdatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
		return usertypepageassociation;
	}
	public static UserTypePageAssoDTO setUserTypePageDTO(Usertypepageassociation usertypepageassociation){
		UserTypePageAssoDTO userTypePageAssoDTO = new UserTypePageAssoDTO();
		userTypePageAssoDTO.setId(usertypepageassociation.getId());
		PageDTO pageDTO = new PageDTO();
		pageDTO.setId(usertypepageassociation.getPage().getId());
		pageDTO.setPageName(usertypepageassociation.getPage().getPageName());
		pageDTO.setMenu(usertypepageassociation.getPage().getMenu());
		pageDTO.setSubmenu(usertypepageassociation.getPage().getSubmenu());
		pageDTO.setUrl(usertypepageassociation.getPage().getUrl());
		userTypePageAssoDTO.setPage(pageDTO);
		UserTypeDTO userTypeDTO = new UserTypeDTO();
		userTypeDTO.setId(usertypepageassociation.getUsertype().getId());
		userTypeDTO.setUsertypeName(usertypepageassociation.getUsertype().getUsertypeName());
		userTypePageAssoDTO.setUsertypeId(userTypeDTO);
		userTypePageAssoDTO.setActive(true);
		return userTypePageAssoDTO;
	}
	
}
