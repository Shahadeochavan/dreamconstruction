package com.nextech.erp.dao;

import com.nextech.erp.model.Rawmaterialorder;

public interface RawmaterialorderDao extends SuperDao<Rawmaterialorder>{
	public Rawmaterialorder getRawmaterialorderByIdName(long id ,String rmname) throws Exception;
	
}

