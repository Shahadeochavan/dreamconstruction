package com.nextech.erp.service;

import java.util.List;

import com.nextech.erp.model.Rawmaterialinventoryhistory;

public interface RawmaterialinventoryhistoryService {

	public boolean addRawmaterialinventoryhistory(
			Rawmaterialinventoryhistory rawmaterialinventoryhistory)
			throws Exception;

	public Rawmaterialinventoryhistory getRawmaterialinventoryhistoryById(
			long id) throws Exception;

	public List<Rawmaterialinventoryhistory> getRawmaterialinventoryhistoryList()
			throws Exception;

	public boolean deleteRawmaterialinventoryhistory(long id) throws Exception;

	public Rawmaterialinventoryhistory updateRawmaterialinventoryhistory(
			Rawmaterialinventoryhistory rawmaterialinventoryhistory)
			throws Exception;
}

