package com.nextech.erp.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.nextech.erp.dao.ProductorderassociationDao;
import com.nextech.erp.model.Productorderassociation;
import com.nextech.erp.service.ProductorderassociationService;

public class ProductorderassociationServiceImpl implements
		ProductorderassociationService {
	@Autowired
	ProductorderassociationDao productorderassociationDao;

	@Override
	public Long addProductorderassociation(
			Productorderassociation productorderassociation) throws Exception {
		productorderassociation.setCreatedDate(new Timestamp(new Date()
				.getTime()));
		return productorderassociationDao
				.addProductorderassociation(productorderassociation);
	}

	@Override
	public Productorderassociation getProductorderassociationById(long id)
			throws Exception {
		return productorderassociationDao.getProductorderassociationById(id);
	}

	@Override
	public List<Productorderassociation> getProductorderassociationList()
			throws Exception {
		return productorderassociationDao.getProductorderassociationList();
	}

	@Override
	public boolean deleteProductorderassociation(long id) throws Exception {
		return productorderassociationDao.deleteProductorderassociation(id);
	}

	@Override
	public Productorderassociation updateProductorderassociation(
			Productorderassociation productorderassociation) throws Exception {
		productorderassociation.setUpdatedDate(new Timestamp(new Date()
				.getTime()));
		return productorderassociationDao
				.updateProductorderassociation(productorderassociation);
	}

}
