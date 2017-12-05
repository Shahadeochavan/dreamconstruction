package com.nextech.dreamConstruction.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.nextech.dreamConstruction.dao.UserDao;
import com.nextech.dreamConstruction.dto.UserDTO;
import com.nextech.dreamConstruction.factory.UserFactory;
import com.nextech.dreamConstruction.model.User;
import com.nextech.dreamConstruction.service.UserService;
@Service
@Qualifier("userServiceImpl")
public class UserServiceImpl extends CRUDServiceImpl<User> implements UserService {
	
	@Autowired
	UserDao userdao;

	@Override
	public User findByUserId(String string) throws Exception {
		return null;
	}

	@Override
	public User getUserByUserId(String userid) throws Exception {
		return userdao.getUserByUserId(userid);
	}

	@Override
	public UserDTO getUserByEmail(String email) throws Exception {
		User user = userdao.getUserByEmail(email);
		if(user==null){
			return null;
		}
		UserDTO userDTO = UserFactory.setUserList(user);
		return userDTO;
	}

	@Override
	public User getUserByMobile(String mobile) throws Exception {
		return userdao.getUserByMobile(mobile);
	}

}
