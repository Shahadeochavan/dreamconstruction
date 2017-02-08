package com.nextech.erp.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.StatustransitionDao;
import com.nextech.erp.model.Statustransition;
import com.nextech.erp.service.StatustransitionService;

public class StatustransitionServiceImpl extends CRUDServiceImpl<Statustransition> implements StatustransitionService {

	@Autowired
	StatustransitionDao statustransitionDao;

	@Override
	public Statustransition getStatustransitionByEmail(String email)
			throws Exception {
		return statustransitionDao.getStatustransitionByEmail(email);
	}

}
