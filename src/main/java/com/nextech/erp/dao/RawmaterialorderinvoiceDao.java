package com.nextech.erp.dao;

import java.util.List;

import com.nextech.erp.model.Rawmaterialorder;
import com.nextech.erp.model.Rawmaterialorderinvoice;

public interface RawmaterialorderinvoiceDao {

	public Long addRawmaterialorderinvoice(Rawmaterialorderinvoice Rawmaterialorderinvoice) throws Exception;

	public Rawmaterialorderinvoice getRawmaterialorderinvoiceById(long id) throws Exception;

	public List<Rawmaterialorderinvoice> getRawmaterialorderinvoiceList() throws Exception;

	public boolean deleteRawmaterialorderinvoice(long id) throws Exception;

	public Rawmaterialorderinvoice updateRawmaterialorderinvoice(Rawmaterialorderinvoice Rawmaterialorderinvoice) throws Exception;
	
}