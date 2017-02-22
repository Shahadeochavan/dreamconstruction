package com.nextech.erp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.RawmaterialorderDao;
import com.nextech.erp.model.Rawmaterialorder;
import com.nextech.erp.service.RawmaterialorderService;

public class RawmaterialorderServiceImpl extends CRUDServiceImpl<Rawmaterialorder> implements RawmaterialorderService{
	@Autowired
	RawmaterialorderDao rawmaterialorderDao;
	
	@Override
	public Rawmaterialorder getRawmaterialorderByIdName(long id,String rmname) throws Exception {
		return rawmaterialorderDao.getRawmaterialorderByIdName(id,rmname);
	}

	@Override
	public List<Rawmaterialorder> getRawmaterialorderByStatusId(long statusId)
			throws Exception {
		// TODO Auto-generated method stub
		return rawmaterialorderDao.getRawmaterialorderByStatusId(statusId);
	}

	@Override
	public List<Rawmaterialorder> getRawmaterialorderByQualityCheckStatusId(
			long statusId) throws Exception {
		// TODO Auto-generated method stub
		return rawmaterialorderDao.getRawmaterialorderByQualityCheckStatusId(statusId);
	}

}

