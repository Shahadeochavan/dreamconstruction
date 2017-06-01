package com.nextech.erp.service;

import java.util.List;

import com.nextech.erp.model.BOMRMVendorAssociation;


public interface BOMRMVendorAssociationService extends CRUDService<BOMRMVendorAssociation>{
	
	public List<BOMRMVendorAssociation> getBomRMVendorByBomId(long bomId) throws Exception;

}
