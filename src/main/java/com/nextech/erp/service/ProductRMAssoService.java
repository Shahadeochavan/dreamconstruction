
package com.nextech.erp.service;

import com.nextech.erp.model.Productrawmaterialassociation;

public interface ProductRMAssoService extends CRUDService<Productrawmaterialassociation>{
	
	public Productrawmaterialassociation getPRMAssociationByPidRmid(long pid, long rid) throws Exception;
}
