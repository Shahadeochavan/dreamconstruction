package com.nextech.erp.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.RMVAssoDao;
import com.nextech.erp.model.Rawmaterialvendorassociation;
import com.nextech.erp.service.RMVAssoService;

public class RMVAssoServiceImpl extends CRUDServiceImpl<Rawmaterialvendorassociation> implements RMVAssoService{

	@Autowired
	RMVAssoDao rMVAssoDao;
	@Override
	public Rawmaterialvendorassociation getRMVAssoByVendorIdRMId(
			long vendorId, long rmId) throws Exception {
		// TODO Auto-generated method stub
		return rMVAssoDao.getRMVAssoByVendorIdRMId(vendorId, rmId);
	}
	
}

