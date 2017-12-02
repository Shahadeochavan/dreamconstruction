package com.nextech.systeminventory.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextech.systeminventory.dao.PrVndrAssnDao;
import com.nextech.systeminventory.model.PrVndrAssn;
import com.nextech.systeminventory.service.PrVndrAssnService;

@Service
public class PrVndrAssnServiceImpl extends CRUDServiceImpl<PrVndrAssn> implements PrVndrAssnService{

	@Autowired
	PrVndrAssnDao prVndrAssnDao;
	@Override
	public List<PrVndrAssn> getPrVndrAssnByVendorId(long vendorId)
			throws Exception {
		// TODO Auto-generated method stub
		return prVndrAssnDao.getPrVndrAssnByVendorId(vendorId);
	}
	@Override
	public PrVndrAssn getPrVndrAssnByVendorIdProductId(long vendorId,
			long productId) throws Exception {
		// TODO Auto-generated method stub
		return prVndrAssnDao.getPrVndrAssnByVendorIdProductId(vendorId, productId);
	}
	@Override
	public List<PrVndrAssn> getPrVndrAssnByProductId(long productId)
			throws Exception {
		// TODO Auto-generated method stub
		return prVndrAssnDao.getPrVndrAssnByProductId(productId);
	}
	@Override
	public List<PrVndrAssn> getPrVndrAssnByprice(long productId,float price) throws Exception {
		// TODO Auto-generated method stub
		return prVndrAssnDao.getPrVndrAssnByprice(productId,price);
	}

}
