package com.nextech.erp.dao;

import com.nextech.erp.model.Rawmaterialvendorassociation;

public interface RMVAssoDao extends SuperDao<Rawmaterialvendorassociation>{
	
	public Rawmaterialvendorassociation getRMVAssoByVendorIdRMId(long  vendorId, long rmId) throws Exception;
	
}
