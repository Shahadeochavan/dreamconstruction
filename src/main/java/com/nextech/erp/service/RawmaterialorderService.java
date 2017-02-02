package com.nextech.erp.service;

import java.util.List;

import com.nextech.erp.model.Rawmaterialorder;

public interface RawmaterialorderService  {
	public Integer addRawmaterialorder(Rawmaterialorder Rawmaterialorder) throws Exception;

	public Rawmaterialorder getRawmaterialorderById(long id) throws Exception;

	public List<Rawmaterialorder> getRawmaterialorderList() throws Exception;

	public boolean deleteRawmaterialorder(long id) throws Exception;

	public Rawmaterialorder updateRawmaterialorder(Rawmaterialorder Rawmaterialorder) throws Exception;
}

