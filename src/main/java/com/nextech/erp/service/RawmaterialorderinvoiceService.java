package com.nextech.erp.service;

import java.util.List;

import com.nextech.erp.model.Rawmaterialorderinvoice;

public interface RawmaterialorderinvoiceService {

	public Long addRawmaterialorderinvoice(
			Rawmaterialorderinvoice Rawmaterialorderinvoice) throws Exception;

	public Rawmaterialorderinvoice getRawmaterialorderinvoiceById(long id)
			throws Exception;

	public List<Rawmaterialorderinvoice> getRawmaterialorderinvoiceList()
			throws Exception;

	public boolean deleteRawmaterialorderinvoice(long id) throws Exception;

	public Rawmaterialorderinvoice updateRawmaterialorderinvoice(
			Rawmaterialorderinvoice Rawmaterialorderinvoice) throws Exception;

}