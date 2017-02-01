package com.nextech.erp.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.RawmaterialorderassociationDao;
import com.nextech.erp.model.Rawmaterialorderassociation;
import com.nextech.erp.service.RawmaterialorderassociationService;

public class RawmaterialorderassociationServiceImpl implements
		RawmaterialorderassociationService {

	@Autowired
	RawmaterialorderassociationDao RawmaterialorderassociationDao;

	@Override
	public boolean addRawmaterialorderassociation(
			Rawmaterialorderassociation Rawmaterialorderassociation)
			throws Exception {
		Rawmaterialorderassociation.setCreatedDate(new Timestamp(new Date()
				.getTime()));
		return RawmaterialorderassociationDao
				.addRawmaterialorderassociation(Rawmaterialorderassociation);
	}

	@Override
	public Rawmaterialorderassociation getRawmaterialorderassociationById(
			long id) throws Exception {
		return RawmaterialorderassociationDao
				.getRawmaterialorderassociationById(id);
	}

	@Override
	public List<Rawmaterialorderassociation> getRawmaterialorderassociationList()
			throws Exception {
		return RawmaterialorderassociationDao
				.getRawmaterialorderassociationList();
	}

	@Override
	public boolean deleteRawmaterialorderassociation(long id) throws Exception {
		return RawmaterialorderassociationDao
				.deleteRawmaterialorderassociation(id);
	}

	@Override
	public Rawmaterialorderassociation updateRawmaterialorderassociation(
			Rawmaterialorderassociation Rawmaterialorderassociation)
			throws Exception {
		Rawmaterialorderassociation.setUpdatedDate(new Timestamp(new Date()
				.getTime()));
		return RawmaterialorderassociationDao
				.updateRawmaterialorderassociation(Rawmaterialorderassociation);
	}

}
