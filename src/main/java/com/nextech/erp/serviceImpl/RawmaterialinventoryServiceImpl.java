package com.nextech.erp.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.RawmaterialinventoryDao;
import com.nextech.erp.model.Rawmaterialinventory;
import com.nextech.erp.service.RawmaterialinventoryService;

public class RawmaterialinventoryServiceImpl implements
		RawmaterialinventoryService {

	@Autowired
	RawmaterialinventoryDao rawmaterialinventoryDao;

	@Override
	public Long addRawmaterialinventory(
			Rawmaterialinventory rawmaterialinventory) throws Exception {
		rawmaterialinventory
				.setCreatedDate(new Timestamp(new Date().getTime()));
		return rawmaterialinventoryDao
				.addRawmaterialinventory(rawmaterialinventory);
	}

	@Override
	public Rawmaterialinventory getRawmaterialinventoryById(long id)
			throws Exception {
		return rawmaterialinventoryDao.getRawmaterialinventoryById(id);
	}

	@Override
	public List<Rawmaterialinventory> getRawmaterialinventoryList()
			throws Exception {
		return rawmaterialinventoryDao.getRawmaterialinventoryList();
	}

	@Override
	public boolean deleteRawmaterialinventory(long id) throws Exception {
		return rawmaterialinventoryDao.deleteRawmaterialinventory(id);
	}

	@Override
	public Rawmaterialinventory updateRawmaterialinventory(
			Rawmaterialinventory rawmaterialinventory) throws Exception {
		rawmaterialinventory
				.setUpdatedDate(new Timestamp(new Date().getTime()));
		return rawmaterialinventoryDao
				.updateRawmaterialinventory(rawmaterialinventory);
	}

}
