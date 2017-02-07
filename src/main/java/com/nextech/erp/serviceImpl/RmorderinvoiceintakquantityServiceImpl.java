package com.nextech.erp.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.RmorderinvoiceintakquantityDao;
import com.nextech.erp.model.Rmorderinvoiceintakquantity;
import com.nextech.erp.service.RmorderinvoiceintakquantityService;

public class RmorderinvoiceintakquantityServiceImpl implements
		RmorderinvoiceintakquantityService {
	@Autowired
	RmorderinvoiceintakquantityDao rmorderinvoiceintakquantitydao;

	@Override
	public Long addRmorderinvoiceintakquantity(
			Rmorderinvoiceintakquantity rmorderinvoiceintakquantity)
			throws Exception {
		rmorderinvoiceintakquantity.setCreatedDate(new Timestamp(new Date()
				.getTime()));
		return rmorderinvoiceintakquantitydao
				.addRmorderinvoiceintakquantity(rmorderinvoiceintakquantity);
	}

	@Override
	public Rmorderinvoiceintakquantity getRmorderinvoiceintakquantityById(
			long id) throws Exception {
		return rmorderinvoiceintakquantitydao
				.getRmorderinvoiceintakquantityById(id);
	}

	@Override
	public List<Rmorderinvoiceintakquantity> getRmorderinvoiceintakquantityList()
			throws Exception {
		return rmorderinvoiceintakquantitydao
				.getRmorderinvoiceintakquantityList();
	}

	@Override
	public boolean deleteRmorderinvoiceintakquantity(long id) throws Exception {
		return rmorderinvoiceintakquantitydao
				.deleteRmorderinvoiceintakquantity(id);
	}

	@Override
	public Rmorderinvoiceintakquantity updateRmorderinvoiceintakquantity(
			Rmorderinvoiceintakquantity rmorderinvoiceintakquantity)
			throws Exception {
		rmorderinvoiceintakquantity.setUpdatedDate(new Timestamp(new Date()
				.getTime()));
		return rmorderinvoiceintakquantitydao
				.updateRmorderinvoiceintakquantity(rmorderinvoiceintakquantity);
	}
}