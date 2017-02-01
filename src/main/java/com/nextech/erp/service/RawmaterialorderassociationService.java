package com.nextech.erp.service;

import java.util.List;

import com.nextech.erp.model.Rawmaterialorderassociation;

public interface RawmaterialorderassociationService {
	public boolean addRawmaterialorderassociation(
			Rawmaterialorderassociation Rawmaterialorderassociation)
			throws Exception;

	public Rawmaterialorderassociation getRawmaterialorderassociationById(
			long id) throws Exception;

	public List<Rawmaterialorderassociation> getRawmaterialorderassociationList()
			throws Exception;

	public boolean deleteRawmaterialorderassociation(long id) throws Exception;

	public Rawmaterialorderassociation updateRawmaterialorderassociation(
			Rawmaterialorderassociation Rawmaterialorderassociation)
			throws Exception;
}