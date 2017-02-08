package com.nextech.erp.dao;

import java.util.List;

import com.nextech.erp.model.Rawmaterialorderassociation;

public interface RawmaterialorderassociationDao {
	public Long addRawmaterialorderassociation(
			Rawmaterialorderassociation rawmaterialorderassociationService)
			throws Exception;

	public Rawmaterialorderassociation getRawmaterialorderassociationById(
			long id) throws Exception;

	public List<Rawmaterialorderassociation> getRawmaterialorderassociationList()
			throws Exception;

	public boolean deleteRawmaterialorderassociation(long id) throws Exception;

	public Rawmaterialorderassociation updateRawmaterialorderassociation(
			Rawmaterialorderassociation rawmaterialorderassociationService)
			throws Exception;

	public List<Rawmaterialorderassociation> getRawmaterialorderassociationByRMOId(long id) throws Exception;
}
