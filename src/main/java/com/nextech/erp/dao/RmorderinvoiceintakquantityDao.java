package com.nextech.erp.dao;

import java.util.List;

import com.nextech.erp.model.Rmorderinvoiceintakquantity;

public interface RmorderinvoiceintakquantityDao {

	public Long addRmorderinvoiceintakquantity(
			Rmorderinvoiceintakquantity Rmorderinvoiceintakquantity)
			throws Exception;

	public Rmorderinvoiceintakquantity getRmorderinvoiceintakquantityById(
			long id) throws Exception;

	public List<Rmorderinvoiceintakquantity> getRmorderinvoiceintakquantityList()
			throws Exception;

	public boolean deleteRmorderinvoiceintakquantity(long id) throws Exception;

	public Rmorderinvoiceintakquantity updateRmorderinvoiceintakquantity(
			Rmorderinvoiceintakquantity Rmorderinvoiceintakquantity)
			throws Exception;
}