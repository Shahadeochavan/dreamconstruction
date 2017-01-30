package com.nextech.erp.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.nextech.erp.dao.ProductRMAssoDao;
import com.nextech.erp.model.Productrawmaterialassociation;
import com.nextech.erp.service.ProductRMAssoService;

public class ProductRMAssoServiceImpl implements ProductRMAssoService {

	@Autowired
	ProductRMAssoDao productrmassDao;

	@Override
	public boolean addProductrawmaterialassociation(
			Productrawmaterialassociation productrawmaterialassociation)
			throws Exception {
		productrawmaterialassociation.setCreatedDate(new Timestamp(new Date()
				.getTime()));
		return productrmassDao
				.addProductrawmaterialassociation(productrawmaterialassociation);
	}

	@Override
	public Productrawmaterialassociation getProductrawmaterialassociationById(
			long id) throws Exception {
		return productrmassDao.getProductrawmaterialassociationById(id);
	}

	@Override
	public List<Productrawmaterialassociation> getProductrawmaterialassociationList()
			throws Exception {
		return productrmassDao.getProductrawmaterialassociationList();
	}

	@Override
	public boolean deleteProductrawmaterialassociation(long id)
			throws Exception {
		return productrmassDao.deleteProductrawmaterialassociation(id);
	}

	@Override
	public Productrawmaterialassociation updateProductrawmaterialassociation(
			Productrawmaterialassociation productrawmaterialassociation)
			throws Exception {
		productrawmaterialassociation.setUpdatedDate(new Timestamp(new Date()
				.getTime()));
		return productrmassDao
				.updateProductrawmaterialassociation(productrawmaterialassociation);
	}
	@Override
	public Productrawmaterialassociation getPRAssociationById(long pid,long rid) throws Exception {
		return productrmassDao.getPRMAssociationByPidRmid(pid,rid);
	}


}
