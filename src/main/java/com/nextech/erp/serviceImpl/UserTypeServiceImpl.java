package com.nextech.erp.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.nextech.erp.dao.UserTypeDao;
import com.nextech.erp.model.Usertype;
import com.nextech.erp.service.UserTypeService;

public class UserTypeServiceImpl implements UserTypeService {
	@Autowired
	UserTypeDao userTypeDao;

	@Override
	public Long addUsertype(Usertype userType) throws Exception {
		userType.setCreatedDate(new Timestamp(new Date().getTime()));
		return userTypeDao.addUsertype(userType);
	}

	@Override
	public Usertype getUsertypeById(long id) throws Exception {
		return userTypeDao.getUsertypeById(id);
	}

	@Override
	public List<Usertype> getUsertypeList() throws Exception {
		return userTypeDao.getUsertypeList();
	}

	@Override
	public boolean deleteUsertype(long id) throws Exception {
		return userTypeDao.deleteUsertype(id);
	}

	@Override
	public Usertype updateUsertype(Usertype userType) throws Exception {
		userType.setUpdatedDate(new Timestamp(new Date().getTime()));
		return userTypeDao.updateUsertype(userType);
	}
}
