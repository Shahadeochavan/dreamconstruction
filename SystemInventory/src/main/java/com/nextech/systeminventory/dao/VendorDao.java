package com.nextech.systeminventory.dao;

import com.nextech.systeminventory.model.Vendor;

public interface VendorDao extends SuperDao<Vendor>{

	public Vendor getVendorByCompanyName(String companyName) throws Exception;

	public Vendor getVendorByEmail(String email) throws Exception;

	public Vendor getVendorByName(String vendorName) throws Exception;
}
