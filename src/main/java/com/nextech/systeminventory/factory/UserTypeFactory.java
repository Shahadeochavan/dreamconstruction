package com.nextech.systeminventory.factory;

import javax.servlet.http.HttpServletRequest;

import com.nextech.systeminventory.dto.UserTypeDTO;
import com.nextech.systeminventory.model.Usertype;



public class UserTypeFactory {
	
	public static Usertype setUserType(UserTypeDTO userTypeDTO,HttpServletRequest request){
		Usertype usertype = new Usertype();
		usertype.setId(userTypeDTO.getId());
		usertype.setDescription(userTypeDTO.getDescription());
		usertype.setUsertypeName(userTypeDTO.getUsertypeName());
		usertype.setCreatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
		usertype.setIsactive(true);
		return usertype;
	}
	
	public static Usertype setUserTypeUpdate(UserTypeDTO userTypeDTO,HttpServletRequest request){
		Usertype usertype = new Usertype();
		usertype.setId(userTypeDTO.getId());
		usertype.setDescription(userTypeDTO.getDescription());
		usertype.setUsertypeName(userTypeDTO.getUsertypeName());
		usertype.setUpdatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
		usertype.setIsactive(true);
		return usertype;
	}
	
	public static UserTypeDTO setUserTypeDto(Usertype usertype){
		UserTypeDTO userTypeDTO = new UserTypeDTO();
		userTypeDTO.setId(usertype.getId());
		userTypeDTO.setDescription(usertype.getDescription());
		userTypeDTO.setUsertypeName(usertype.getUsertypeName());
		return userTypeDTO;
	}

}
