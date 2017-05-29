package com.nextech.erp.dto;

import com.nextech.erp.model.Rawmaterial;
import com.nextech.erp.model.Vendor;

public class RMVendorData {

	private Rawmaterial rawmaterial;
	private Vendor vendor;

	public Rawmaterial getRawmaterial() {
		return rawmaterial;
	}

	public void setRawmaterial(Rawmaterial rawmaterial) {
		this.rawmaterial = rawmaterial;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

}
