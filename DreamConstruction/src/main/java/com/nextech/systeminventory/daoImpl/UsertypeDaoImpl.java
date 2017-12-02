package com.nextech.systeminventory.daoImpl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nextech.systeminventory.dao.UserTypeDao;
import com.nextech.systeminventory.model.Usertype;

@Repository
@Transactional
public class UsertypeDaoImpl extends SuperDaoImpl<Usertype> implements UserTypeDao {
	
}

