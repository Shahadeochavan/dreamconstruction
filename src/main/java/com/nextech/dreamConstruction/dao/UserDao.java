package com.nextech.dreamConstruction.dao;

import java.util.List;

import com.nextech.dreamConstruction.model.User;

public interface UserDao extends SuperDao<User>{
	public User getUserByUserId(String userId) throws Exception;

	public User getUserByEmail(String email) throws Exception;

	public User getUserByMobile(String mobile) throws Exception;
	
	public List<User> getMultipleUsersById(List<Long> ids) throws Exception;
}
