package com.nextech.erp.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.RawmaterialDao;
import com.nextech.erp.model.Rawmaterial;
import com.nextech.erp.service.RawmaterialService;

public class RawmaterialServiceImpl implements RawmaterialService {
	@Autowired
	RawmaterialDao rawmaterialDao;

	@Override
	public Long addRawmaterial(Rawmaterial rawmaterial) throws Exception {
		rawmaterial.setCreatedDate(new Timestamp(new Date().getTime()));
		return rawmaterialDao.addRawmaterial(rawmaterial);
	}

	@Override
	public Rawmaterial getRawmaterialById(long id) throws Exception {
		return rawmaterialDao.getRawmaterialById(id);
	}

	@Override
	public List<Rawmaterial> getRawmaterialList() throws Exception {
		return rawmaterialDao.getRawmaterialList();
	}

	@Override
	public boolean deleteRawmaterial(long id) throws Exception {
		return rawmaterialDao.deleteRawmaterial(id);
	}

	@Override
	public Rawmaterial updateRawmaterial(Rawmaterial rawmaterial)
			throws Exception {
		rawmaterial.setUpdatedDate(new Timestamp(new Date().getTime()));
		return rawmaterialDao.updateRawmaterial(rawmaterial);
	}

}
