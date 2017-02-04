package com.nextech.erp.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.nextech.erp.dao.VendorDao;
import com.nextech.erp.model.Vendor;
import com.nextech.erp.service.VendorService;

public class VendorServiceImpl implements VendorService {
	@Autowired
	VendorDao vendorDao;

	@Override
	public Long addVendor(Vendor vendor) throws Exception {
		vendor.setCreatedDate(new Timestamp(new Date().getTime()));
		return vendorDao.addVendor(vendor);
	}

	@Override
	public Vendor getVendorById(long id) throws Exception {
		return vendorDao.getVendorById(id);
	}

	@Override
	public List<Vendor> getVendorList() throws Exception {
		return vendorDao.getVendorList();
	}

	@Override
	public boolean deleteVendor(long id) throws Exception {
		return vendorDao.deleteVendor(id);
	}

	@Override
	public Vendor updateVendor(Vendor vendor) throws Exception {
		vendor.setUpdatedDate(new Timestamp(new Date().getTime()));
		return vendorDao.updateVendor(vendor);
	}

	@Override
	public Vendor getVendorByCompanyName(String companyName) throws Exception {
		return vendorDao.getVendorByCompanyName(companyName);
	}

	@Override
	public Vendor getVendorByEmail(String email) throws Exception {
		return vendorDao.getVendorByEmail(email);
	}

}
