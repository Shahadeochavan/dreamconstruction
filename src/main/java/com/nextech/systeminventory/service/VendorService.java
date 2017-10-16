package com.nextech.systeminventory.service;

import com.nextech.systeminventory.model.Vendor;

public interface VendorService extends CRUDService<Vendor>{

	public Vendor getVendorByCompanyName(String companyName) throws Exception;

	public Vendor getVendorByEmail(String email) throws Exception;

	public Vendor getVendorByName(String vendorName) throws Exception;
}
