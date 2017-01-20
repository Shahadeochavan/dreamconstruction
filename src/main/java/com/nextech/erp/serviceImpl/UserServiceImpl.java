package com.nextech.erp.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.UserDao;
import com.nextech.erp.model.User;
import com.nextech.erp.service.UserService;

public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userdao;

	@Override
	public boolean addEntity(User user) throws ConstraintViolationException,Exception {
		user.setCreatedDate(new Timestamp(new Date().getTime()));
//		userValidator.validate(user, new Eerrors);
		return userdao.addEntity(user);
	}

	@Override
	public User getEntityById(long id) throws Exception {
		return userdao.getEntityById(id);
	}

	@Override
	public List<User> getEntityList() throws Exception {
		return userdao.getEntityList();
	}

	@Override
	public boolean deleteEntity(long id) throws Exception {
		return userdao.deleteEntity(id);
	}

	@Override
	public User updateEntity(User user) throws Exception {
		user.setUpdatedDate(new Timestamp(new Date().getTime()));
		return userdao.updateEntity(user);
	}

	@Override
	public User findByUserId(String string) throws Exception {
		return null;
	}
}
