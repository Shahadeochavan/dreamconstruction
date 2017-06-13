package com.nextech.erp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.ReportusertypeassociationDao;
import com.nextech.erp.model.Reportusertypeassociation;
import com.nextech.erp.service.ReportusertypeassociationService;

public class ReportusertypeassociationServiceImpl extends CRUDServiceImpl<Reportusertypeassociation> implements ReportusertypeassociationService {

	
	@Autowired
	ReportusertypeassociationDao reportusertypeassociationDao;
	@Override
	public List<Reportusertypeassociation> getReportByUsertype(long usertypeId) {
		// TODO Auto-generated method stub
		return reportusertypeassociationDao.getReportByUsertype(usertypeId);
	}

}
