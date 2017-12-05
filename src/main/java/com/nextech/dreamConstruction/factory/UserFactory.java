package com.nextech.dreamConstruction.factory;

import javax.servlet.http.HttpServletRequest;

import com.nextech.dreamConstruction.dto.UserDTO;
import com.nextech.dreamConstruction.dto.UserTypeDTO;
import com.nextech.dreamConstruction.model.User;
import com.nextech.dreamConstruction.model.Usertype;




public class UserFactory {

	//TODO CALL TO ADD USER CONTROLLER
	public static User setUser(UserDTO userDTO,HttpServletRequest request) throws Exception{
		User user = new User();
		user.setId(userDTO.getId());
		user.setUserid(userDTO.getUserId());
		user.setPassword(userDTO.getPassword());
		user.setMobile(userDTO.getMobileNo());
		user.setDob(userDTO.getDob());
		user.setEmail(userDTO.getEmailId());
		user.setGender(userDTO.getGender());
		Usertype  usertype = new Usertype();
		usertype.setId(10);
		user.setUsertype(usertype);
		//user.setCreatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
		user.setIsactive(true);
		return user;
	}
	
	public static User setUserUpdate(UserDTO userDTO,HttpServletRequest request) throws Exception{
		User user = new User();
		user.setId(userDTO.getId());
		user.setUserid(userDTO.getUserId());
		user.setPassword(userDTO.getPassword());
		user.setMobile(userDTO.getMobileNo());
		user.setDob(userDTO.getDob());
		user.setEmail(userDTO.getEmailId());
		Usertype  usertype = new Usertype();
		user.setGender(userDTO.getGender());
		usertype.setId(10);
		user.setUsertype(usertype);
		//user.setCreatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
		user.setIsactive(true);
		return user;
	}
	public static UserDTO setUserList(User user){
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setUserId(user.getUserid());
		userDTO.setPassword(user.getPassword());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setMobileNo(user.getMobile());
		userDTO.setDob(user.getDob());
		userDTO.setDoj(user.getDoj());
		userDTO.setEmailId(user.getEmail());
		UserTypeDTO  userTypeDTO = new UserTypeDTO();
		userTypeDTO.setUsertypeName(user.getUsertype().getUsertypeName());
		userTypeDTO.setId(user.getUsertype().getId());
		userDTO.setUserTypeDTO(userTypeDTO);
		userDTO.setActive(true);
		return userDTO;
	}

}
