package com.nextech.dreamConstruction.dao;

import java.util.List;

import com.nextech.dreamConstruction.model.PrVndrAssn;

public interface PrVndrAssnDao  extends SuperDao<PrVndrAssn>{
	
	public List<PrVndrAssn> getPrVndrAssnByVendorId(long vendorId) throws Exception;
	
	public  PrVndrAssn getPrVndrAssnByVendorIdProductId(long vendorId,long productId) throws Exception;
	
	public List<PrVndrAssn> getPrVndrAssnByProductId(long productId) throws Exception;
	
	public List<PrVndrAssn> getPrVndrAssnByprice(long productId,float price) throws Exception;


}
