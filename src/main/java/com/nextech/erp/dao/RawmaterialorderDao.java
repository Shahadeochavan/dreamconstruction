package com.nextech.erp.dao;

import java.util.List;

import com.nextech.erp.model.Rawmaterialorder;

public interface RawmaterialorderDao {
	public Long addRawmaterialorder(Rawmaterialorder Rawmaterialorder) throws Exception;

	public Rawmaterialorder getRawmaterialorderById(long id) throws Exception;

	public List<Rawmaterialorder> getRawmaterialorderList() throws Exception;

	public boolean deleteRawmaterialorder(long id) throws Exception;

	public Rawmaterialorder updateRawmaterialorder(Rawmaterialorder Rawmaterialorder) throws Exception;
	
	public Rawmaterialorder getRawmaterialorderByIdName(long id ,String rmname) throws Exception;
	
	
}

