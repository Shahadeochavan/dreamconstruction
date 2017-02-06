package com.nextech.erp.service;

import java.util.List;

import com.nextech.erp.model.Rawmaterialorderhistory;

public interface RawmaterialorderhistoryService {

	public Long addRawmaterialorderhistory(
			Rawmaterialorderhistory rawmaterialorderhistory) throws Exception;

	public Rawmaterialorderhistory getRawmaterialorderhistoryById(long id)
			throws Exception;

	public List<Rawmaterialorderhistory> getRawmaterialorderhistoryList()
			throws Exception;

	public boolean deleteRawmaterialorderhistory(long id) throws Exception;

	public Rawmaterialorderhistory updateRawmaterialorderhistory(
			Rawmaterialorderhistory rawmaterialorderhistory) throws Exception;
}
