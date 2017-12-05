package com.nextech.dreamConstruction.service;

import java.util.List;

import com.nextech.dreamConstruction.dto.UserDTO;
import com.nextech.dreamConstruction.model.User;

public interface UserService extends CRUDService<User>{

	public User findByUserId(String string) throws Exception;

	public User getUserByUserId(String userId) throws Exception;

	public UserDTO getUserByEmail(String email) throws Exception;

	public User getUserByMobile(String mobile) throws Exception;	
	
}
