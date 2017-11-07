package com.nextech.systeminventory.dao;

import java.util.List;

import com.nextech.systeminventory.model.PrVndrAssn;

public interface PrVndrAssnDao  extends SuperDao<PrVndrAssn>{
	
	public List<PrVndrAssn> getPrVndrAssnByVendorId(long vendorId) throws Exception;
	
	public  PrVndrAssn getPrVndrAssnByVendorIdProductId(long vendorId,long productId) throws Exception;

}
