package com.nextech.erp.dao;

import java.util.List;

import com.nextech.erp.model.Rawmaterialorder;

public interface RawmaterialorderDao extends SuperDao<Rawmaterialorder>{
	public Rawmaterialorder getRawmaterialorderByIdName(long id ,String rmname) throws Exception;
	
	public List<Rawmaterialorder> getRawmaterialorderByStatusId(long statusId) throws Exception;
	
}

