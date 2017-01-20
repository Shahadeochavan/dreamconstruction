package com.nextech.erp.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.StatustransitionDao;
import com.nextech.erp.model.Statustransition;
import com.nextech.erp.service.StatustransitionService;

public class StatustransitionServiceImpl implements StatustransitionService {
	
	@Autowired
	StatustransitionDao statustransitionDao;

	@Override
	public boolean addStatustransition(Statustransition statustransition)
			throws Exception {
		statustransition.setCreatedDate(new Timestamp(new Date().getTime()));
		return statustransitionDao.addStatustransition(statustransition);
	}

	@Override
	public Statustransition getStatustransitionById(long id) throws Exception {
		return statustransitionDao.getStatustransitionById(id);
	}

	@Override
	public List<Statustransition> getStatustransitionList() throws Exception {
		return statustransitionDao.getStatustransitionList();
	}

	@Override
	public boolean deleteStatustransition(long id) throws Exception {
		return statustransitionDao.deleteStatustransition(id);
	}

	@Override
	public Statustransition updateStatustransition(
			Statustransition statustransition) throws Exception {
		statustransition.setUpdatedDate(new Timestamp(new Date().getTime()));
		return statustransitionDao.updateStatustransition(statustransition);
	}

}
