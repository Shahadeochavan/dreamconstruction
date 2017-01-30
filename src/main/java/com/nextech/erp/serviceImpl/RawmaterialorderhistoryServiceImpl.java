package com.nextech.erp.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.nextech.erp.dao.RawmaterialorderhistoryDao;
import com.nextech.erp.model.Rawmaterialorderhistory;
import com.nextech.erp.service.RawmaterialorderhistoryService;

public class RawmaterialorderhistoryServiceImpl implements
		RawmaterialorderhistoryService {
	@Autowired
	RawmaterialorderhistoryDao rawmaterialorderhistoryDao;

	@Override
	public boolean addRawmaterialorderhistory(
			Rawmaterialorderhistory rawmaterialorderhistory) throws Exception {
		rawmaterialorderhistory.setCreatedDate(new Timestamp(new Date()
				.getTime()));
		return rawmaterialorderhistoryDao
				.addRawmaterialorderhistory(rawmaterialorderhistory);
	}

	@Override
	public Rawmaterialorderhistory getRawmaterialorderhistoryById(long id)
			throws Exception {
		return rawmaterialorderhistoryDao.getRawmaterialorderhistoryById(id);
	}

	@Override
	public List<Rawmaterialorderhistory> getRawmaterialorderhistoryList()
			throws Exception {
		return rawmaterialorderhistoryDao.getRawmaterialorderhistoryList();
	}

	@Override
	public boolean deleteRawmaterialorderhistory(long id) throws Exception {
		return rawmaterialorderhistoryDao.deleteRawmaterialorderhistory(id);
	}

	@Override
	public Rawmaterialorderhistory updateRawmaterialorderhistory(
			Rawmaterialorderhistory rawmaterialorderhistory) throws Exception {
		rawmaterialorderhistory.setUpdatedDate(new Timestamp(new Date()
				.getTime()));
		return rawmaterialorderhistoryDao
				.updateRawmaterialorderhistory(rawmaterialorderhistory);
	}

}
