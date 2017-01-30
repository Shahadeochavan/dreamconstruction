package com.nextech.erp.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.RawmaterialorderDao;
import com.nextech.erp.model.Rawmaterialorder;
import com.nextech.erp.service.RawmaterialorderService;

public class RawmaterialorderServiceImpl implements RawmaterialorderService{
	@Autowired
	RawmaterialorderDao rawmaterialorderDao;

	@Override
	public boolean addRawmaterialorder(Rawmaterialorder rawmaterialorder) throws Exception {
		rawmaterialorder.setCreatedDate(new Timestamp(new Date().getTime()));
		return rawmaterialorderDao.addRawmaterialorder(rawmaterialorder);
	}

	@Override
	public Rawmaterialorder getRawmaterialorderById(long id) throws Exception {
		return rawmaterialorderDao.getRawmaterialorderById(id);
	}

	@Override
	public List<Rawmaterialorder> getRawmaterialorderList() throws Exception {
		return rawmaterialorderDao.getRawmaterialorderList();
	}

	@Override
	public boolean deleteRawmaterialorder(long id) throws Exception {
		return rawmaterialorderDao.deleteRawmaterialorder(id);
	}

	@Override
	public Rawmaterialorder updateRawmaterialorder(Rawmaterialorder rawmaterialorder) throws Exception {
		rawmaterialorder.setUpdatedDate(new Timestamp(new Date().getTime()));
		return rawmaterialorderDao.updateRawmaterialorder(rawmaterialorder);
	}

}

