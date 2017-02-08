package com.nextech.erp.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.RawmaterialorderassociationDao;
import com.nextech.erp.model.Rawmaterialorderassociation;
import com.nextech.erp.service.RawmaterialorderassociationService;

public class RawmaterialorderassociationServiceImpl extends CRUDServiceImpl<Rawmaterialorderassociation> implements RawmaterialorderassociationService {

	@Autowired
	RawmaterialorderassociationDao RawmaterialorderassociationDao;

}
