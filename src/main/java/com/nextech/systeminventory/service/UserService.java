package com.nextech.systeminventory.service;

import java.util.List;

import com.nextech.systeminventory.model.User;

public interface UserService extends CRUDService<User>{

	public User findByUserId(String string) throws Exception;

	public User getUserByUserId(String userId) throws Exception;

	public User getUserByEmail(String email) throws Exception;

	public User getUserByMobile(String mobile) throws Exception;	
	
}
