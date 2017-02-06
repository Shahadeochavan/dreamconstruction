package com.nextech.erp.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.nextech.erp.dao.RMVAssoDao;
import com.nextech.erp.model.Rawmaterialvendorassociation;
import com.nextech.erp.service.RMVAssoService;

public class RMVAssoServiceImpl implements RMVAssoService{
	@Autowired
	RMVAssoDao rmvAssoDao;

	@Override
	public Long addRawmaterialvendorassociation(Rawmaterialvendorassociation rawmaterialvendorassociation) throws Exception {
		rawmaterialvendorassociation.setCreatedDate(new Timestamp(new Date().getTime()));
		return rmvAssoDao.addRawmaterialvendorassociation(rawmaterialvendorassociation);
	}

	@Override
	public Rawmaterialvendorassociation getRawmaterialvendorassociationById(long id) throws Exception {
		return rmvAssoDao.getRawmaterialvendorassociationById(id);
	}

	@Override
	public List<Rawmaterialvendorassociation> getRawmaterialvendorassociationList() throws Exception {
		return rmvAssoDao.getRawmaterialvendorassociationList();
	}

	@Override
	public boolean deleteRawmaterialvendorassociation(long id) throws Exception {
		return rmvAssoDao.deleteRawmaterialvendorassociation(id);
	}

	@Override
	public Rawmaterialvendorassociation updateRawmaterialvendorassociation(Rawmaterialvendorassociation rawmaterialvendorassociation) throws Exception {
		rawmaterialvendorassociation.setUpdatedDate(new Timestamp(new Date().getTime()));
		return rmvAssoDao.updateRawmaterialvendorassociation(rawmaterialvendorassociation);
	}

}

