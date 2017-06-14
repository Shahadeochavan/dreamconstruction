package com.nextech.erp.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.ReportusertypeassociationDao;
import com.nextech.erp.model.Reportoutputassociation;
import com.nextech.erp.service.ReptOptAssoService;

public class ReptOptAssoServiceImpl extends CRUDServiceImpl<Reportoutputassociation> implements ReptOptAssoService {

	
	@Autowired
	ReportusertypeassociationDao reportusertypeassociationDao;
	
}
