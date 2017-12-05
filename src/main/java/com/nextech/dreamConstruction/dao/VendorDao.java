package com.nextech.dreamConstruction.dao;

import com.nextech.dreamConstruction.model.Vendor;

public interface VendorDao extends SuperDao<Vendor>{

	public Vendor getVendorByCompanyName(String companyName) throws Exception;

	public Vendor getVendorByEmail(String email) throws Exception;

	public Vendor getVendorByName(String vendorName) throws Exception;
}
