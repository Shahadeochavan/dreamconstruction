package com.nextech.erp.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.nextech.erp.dao.UsertypepageassociationDao;
import com.nextech.erp.model.Usertypepageassociation;
import com.nextech.erp.service.UsertypepageassociationService;

public class UsertypepageassociationServiceImpl implements
		UsertypepageassociationService {
	@Autowired
	UsertypepageassociationDao usertypepageassociationDao;

	@Override
	public Long addPageAss(Usertypepageassociation usertypepageassociation)
			throws Exception {
		usertypepageassociation.setCreatedDate(new Timestamp(new Date()
				.getTime()));
		return usertypepageassociationDao.addPageAss(usertypepageassociation);
	}

	@Override
	public Usertypepageassociation getPageAssById(long id) throws Exception {
		return usertypepageassociationDao.getPageAssById(id);
	}

	@Override
	public List<Usertypepageassociation> getPageAssList() throws Exception {
		return usertypepageassociationDao.getPageAssList();
	}

	@Override
	public boolean deletePageAss(long id) throws Exception {
		return usertypepageassociationDao.deletePageAss(id);
	}

	@Override
	public Usertypepageassociation updatePageAss(
			Usertypepageassociation usertypepageassociation) throws Exception {
		usertypepageassociation.setUpdatedDate(new Timestamp(new Date()
				.getTime()));
		return usertypepageassociationDao
				.updatePageAss(usertypepageassociation);
	}
}
