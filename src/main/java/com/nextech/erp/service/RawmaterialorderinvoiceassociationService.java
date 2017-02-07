package com.nextech.erp.service;

import java.util.List;

import com.nextech.erp.model.Rawmaterialorderinvoiceassociation;

public interface RawmaterialorderinvoiceassociationService {

	public Long addRawmaterialorderinvoiceassociation(
			Rawmaterialorderinvoiceassociation Rawmaterialorderinvoiceassociation)
			throws Exception;

	public Rawmaterialorderinvoiceassociation getRawmaterialorderinvoiceassociationById(
			long id) throws Exception;

	public List<Rawmaterialorderinvoiceassociation> getRawmaterialorderinvoiceassociationList()
			throws Exception;

	public boolean deleteRawmaterialorderinvoiceassociation(long id)
			throws Exception;

	public Rawmaterialorderinvoiceassociation updateRawmaterialorderinvoiceassociation(
			Rawmaterialorderinvoiceassociation Rawmaterialorderinvoiceassociation)
			throws Exception;
}
