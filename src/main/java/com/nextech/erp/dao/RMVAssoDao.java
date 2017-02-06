package com.nextech.erp.dao;

import java.util.List;

import com.nextech.erp.model.Rawmaterialvendorassociation;

public interface RMVAssoDao {
	public Long addRawmaterialvendorassociation(
			Rawmaterialvendorassociation rawmaterialvendorassociation)
			throws Exception;

	public Rawmaterialvendorassociation getRawmaterialvendorassociationById(
			long id) throws Exception;

	public List<Rawmaterialvendorassociation> getRawmaterialvendorassociationList()
			throws Exception;

	public boolean deleteRawmaterialvendorassociation(long id) throws Exception;

	public Rawmaterialvendorassociation updateRawmaterialvendorassociation(
			Rawmaterialvendorassociation rawmaterialvendorassociation)
			throws Exception;
}
