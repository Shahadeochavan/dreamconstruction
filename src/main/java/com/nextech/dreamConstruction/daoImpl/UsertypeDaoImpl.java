package com.nextech.dreamConstruction.daoImpl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nextech.dreamConstruction.dao.UserTypeDao;
import com.nextech.dreamConstruction.model.Usertype;

@Repository
@Transactional
public class UsertypeDaoImpl extends SuperDaoImpl<Usertype> implements UserTypeDao {
	
}

