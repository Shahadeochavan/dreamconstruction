package com.nextech.erp.service;

import com.nextech.erp.model.Rawmaterialorderinvoiceassociation;

public interface RawmaterialorderinvoiceassociationService {

	public Long addRawmaterialorderinvoiceassociation(Rawmaterialorderinvoiceassociation Rawmaterialorderinvoiceassociation) throws Exception;

	public Rawmaterialorderinvoiceassociation getRawmaterialorderinvoiceassociationById(long id) throws Exception;
}
