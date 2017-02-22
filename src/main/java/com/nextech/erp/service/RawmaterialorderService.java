package com.nextech.erp.service;

import java.util.List;

import com.nextech.erp.model.Rawmaterialorder;

public interface RawmaterialorderService extends CRUDService<Rawmaterialorder> {
	
	public Rawmaterialorder getRawmaterialorderByIdName(long id ,String rmname) throws Exception;
	
	public List<Rawmaterialorder> getRawmaterialorderByStatusId(long statusId) throws Exception;
}

