package com.nextech.erp.service;

import java.util.List;

import com.nextech.erp.model.Rawmaterialinventory;

public interface RawmaterialinventoryService {

	public boolean addRawmaterialinventory(
			Rawmaterialinventory rawmaterialinventory) throws Exception;

	public Rawmaterialinventory getRawmaterialinventoryById(long id)
			throws Exception;

	public List<Rawmaterialinventory> getRawmaterialinventoryList()
			throws Exception;

	public boolean deleteRawmaterialinventory(long id) throws Exception;

	public Rawmaterialinventory updateRawmaterialinventory(
			Rawmaterialinventory rawmaterialinventory) throws Exception;
}