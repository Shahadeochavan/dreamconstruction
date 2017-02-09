package com.nextech.erp.dao;

import com.nextech.erp.model.Productrawmaterialassociation;

public interface ProductRMAssoDao extends SuperDao<Productrawmaterialassociation>{

	public Productrawmaterialassociation getPRMAssociationByPidRmid(long pid, long rmid) throws Exception;
}
