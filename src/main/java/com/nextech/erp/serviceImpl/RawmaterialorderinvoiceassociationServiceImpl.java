package com.nextech.erp.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.RawmaterialorderinvoiceassociationDao;
import com.nextech.erp.model.Rawmaterialorderinvoiceassociation;
import com.nextech.erp.service.RawmaterialorderinvoiceassociationService;

public class RawmaterialorderinvoiceassociationServiceImpl implements RawmaterialorderinvoiceassociationService {

	@Autowired
	RawmaterialorderinvoiceassociationDao rawmaterialorderinvoiceassociationdao;

	@Override
	public Long addRawmaterialorderinvoiceassociation(
			Rawmaterialorderinvoiceassociation rawmaterialorderinvoiceassociation) throws Exception {
		rawmaterialorderinvoiceassociation.setCreatedDate(new Timestamp(new Date().getTime()));
		return rawmaterialorderinvoiceassociationdao.add(rawmaterialorderinvoiceassociation);
	}

	@Override
	public Rawmaterialorderinvoiceassociation getRawmaterialorderinvoiceassociationById(long id) throws Exception {
		return rawmaterialorderinvoiceassociationdao.getById(Rawmaterialorderinvoiceassociation.class,id);
	}
}