package com.nextech.erp.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.RawmaterialorderinvoiceDao;
import com.nextech.erp.model.Rawmaterialorderinvoice;
import com.nextech.erp.service.RawmaterialorderinvoiceService;

public class RawmaterialorderinvoiceServiceImpl implements RawmaterialorderinvoiceService {

	@Autowired
	RawmaterialorderinvoiceDao Rawmaterialorderinvoicedao;
	
	@Override
	public Long addRawmaterialorderinvoice(Rawmaterialorderinvoice Rawmaterialorderinvoice) throws Exception {
		Rawmaterialorderinvoice.setCreatedDate(new Timestamp(new Date().getTime()));
		return Rawmaterialorderinvoicedao.addRawmaterialorderinvoice(Rawmaterialorderinvoice);
	}

	@Override
	public Rawmaterialorderinvoice getRawmaterialorderinvoiceById(long id) throws Exception {
		return Rawmaterialorderinvoicedao.getRawmaterialorderinvoiceById(id);
	}

	@Override
	public List<Rawmaterialorderinvoice> getRawmaterialorderinvoiceList() throws Exception {
		return Rawmaterialorderinvoicedao.getRawmaterialorderinvoiceList();
	}

	@Override
	public boolean deleteRawmaterialorderinvoice(long id) throws Exception {
		return Rawmaterialorderinvoicedao.deleteRawmaterialorderinvoice(id);
	}
	@Override
	public Rawmaterialorderinvoice updateRawmaterialorderinvoice(Rawmaterialorderinvoice Rawmaterialorderinvoice)throws Exception{
		Rawmaterialorderinvoice.setUpdatedDate(new Timestamp(new Date().getTime()));
		return Rawmaterialorderinvoicedao.updateRawmaterialorderinvoice(Rawmaterialorderinvoice);
	}
}