package com.nextech.erp.dao;

import java.util.List;

import com.nextech.erp.model.BOMRMVendorAssociation;

public interface BOMRMVendorAssociationDao extends SuperDao<BOMRMVendorAssociation> {
	
	public List<BOMRMVendorAssociation> getBomRMVendorByBomId(long bomId) throws Exception;

}
