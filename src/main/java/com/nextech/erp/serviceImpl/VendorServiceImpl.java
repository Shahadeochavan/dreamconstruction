package com.nextech.erp.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.VendorDao;
import com.nextech.erp.model.Vendor;
import com.nextech.erp.service.VendorService;

public class VendorServiceImpl extends CRUDServiceImpl<Vendor> implements VendorService {
	@Autowired
	VendorDao vendorDao;

	@Override
	public Vendor getVendorByCompanyName(String companyName) throws Exception {
		return vendorDao.getVendorByCompanyName(companyName);
	}

	@Override
	public Vendor getVendorByEmail(String email) throws Exception {
		return vendorDao.getVendorByEmail(email);
	}

}
