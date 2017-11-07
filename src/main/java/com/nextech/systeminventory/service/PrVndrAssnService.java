package com.nextech.systeminventory.service;

import java.util.List;

import com.nextech.systeminventory.model.PrVndrAssn;

public interface PrVndrAssnService extends CRUDService<PrVndrAssn> {
	
	public List<PrVndrAssn> getPrVndrAssnByVendorId(long vendorId) throws Exception;
	
	public  PrVndrAssn getPrVndrAssnByVendorIdProductId(long vendorId,long productId) throws Exception;

}
