package com.nextech.erp.service;

import java.util.List;

import com.nextech.erp.model.Rmorderinvoiceintakquantity;

public interface RmorderinvoiceintakquantityService {

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