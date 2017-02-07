package com.nextech.erp.dao;

import com.nextech.erp.model.User;

public interface UserDao extends SuperDao<User>{
	public User getUserByUserId(String userId) throws Exception;

	public User getUserByEmail(String email) throws Exception;

	public User getUserByMobile(String mobile) throws Exception;
}
