package com.nextech.systeminventory.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextech.systeminventory.dao.VendorDao;
import com.nextech.systeminventory.model.Vendor;
import com.nextech.systeminventory.service.VendorService;
@Service
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

	@Override
	public Vendor getVendorByName(String vendorName) throws Exception {
		// TODO Auto-generated method stub
		return vendorDao.getVendorByName(vendorName);
	}

}
