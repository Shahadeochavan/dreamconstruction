package com.nextech.erp.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.RawmaterialinventoryhistoryDao;
import com.nextech.erp.model.Rawmaterialinventoryhistory;
import com.nextech.erp.service.RawmaterialinventoryhistoryService;

public class RawmaterialinventoryhistoryServiceImpl implements
		RawmaterialinventoryhistoryService {
	@Autowired
	RawmaterialinventoryhistoryDao rawmaterialinventoryhistoryDao;

	@Override
	public boolean addRawmaterialinventoryhistory(
			Rawmaterialinventoryhistory rawmaterialinventoryhistory)
			throws Exception {
		rawmaterialinventoryhistory.setCreatedDate(new Timestamp(new Date()
				.getTime()));
		return rawmaterialinventoryhistoryDao
				.addRawmaterialinventoryhistory(rawmaterialinventoryhistory);
	}

	@Override
	public Rawmaterialinventoryhistory getRawmaterialinventoryhistoryById(
			long id) throws Exception {
		return rawmaterialinventoryhistoryDao
				.getRawmaterialinventoryhistoryById(id);
	}

	@Override
	public List<Rawmaterialinventoryhistory> getRawmaterialinventoryhistoryList()
			throws Exception {
		return rawmaterialinventoryhistoryDao
				.getRawmaterialinventoryhistoryList();
	}

	@Override
	public boolean deleteRawmaterialinventoryhistory(long id) throws Exception {
		return rawmaterialinventoryhistoryDao
				.deleteRawmaterialinventoryhistory(id);
	}

	@Override
	public Rawmaterialinventoryhistory updateRawmaterialinventoryhistory(
			Rawmaterialinventoryhistory rawmaterialinventoryhistory)
			throws Exception {
		rawmaterialinventoryhistory.setUpdatedDate(new Timestamp(new Date()
				.getTime()));
		return rawmaterialinventoryhistoryDao
				.updateRawmaterialinventoryhistory(rawmaterialinventoryhistory);
	}

}
