package com.nextech.erp.service;

import com.nextech.erp.model.Rawmaterialvendorassociation;

public interface RMVAssoService extends CRUDService<Rawmaterialvendorassociation>{
	
	public Rawmaterialvendorassociation getRMVAssoByVendorIdRMId(long vendorId,long rmId) throws Exception;
	
}