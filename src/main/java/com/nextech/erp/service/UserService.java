package com.nextech.erp.service;

import java.util.List;

import com.nextech.erp.model.User;

public interface UserService {
	public boolean addEntity(User user) throws Exception;

	public User getEntityById(long id) throws Exception;

	public List<User> getEntityList() throws Exception;

	public boolean deleteEntity(long id) throws Exception;

	public User updateEntity(User user) throws Exception;

	public User findByUserId(String string) throws Exception;

	public User getUserByUserId(String userId) throws Exception;

	public User getUserByEmail(String email) throws Exception;

	public User getUserByMobile(String mobile) throws Exception;
}
