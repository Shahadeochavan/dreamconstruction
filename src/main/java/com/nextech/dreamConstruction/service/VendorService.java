package com.nextech.dreamConstruction.service;

import com.nextech.dreamConstruction.dto.VendorDTO;
import com.nextech.dreamConstruction.model.Vendor;

public interface VendorService extends CRUDService<Vendor>{

	public Vendor getVendorByCompanyName(String companyName) throws Exception;

	public Vendor getVendorByEmail(String email) throws Exception;

	public Vendor getVendorByName(String vendorName) throws Exception;
	
	public VendorDTO getVendorById(long id) throws Exception;
}
