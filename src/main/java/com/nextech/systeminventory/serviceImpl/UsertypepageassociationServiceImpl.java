package com.nextech.systeminventory.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextech.systeminventory.dao.UsertypepageassociationDao;
import com.nextech.systeminventory.model.Usertypepageassociation;
import com.nextech.systeminventory.service.UsertypepageassociationService;
@Service
public class UsertypepageassociationServiceImpl extends CRUDServiceImpl<Usertypepageassociation> implements
		UsertypepageassociationService {

	@Autowired
	UsertypepageassociationDao usertypepageassociationDao;
	
	@Override
	public List<Usertypepageassociation> getPagesByUsertype(long usertypeId) {
		return usertypepageassociationDao.getPagesByUsertype(usertypeId);
	}

	@Override
	public boolean checkPageAccess(long usertypeId, long pageId) {
		return usertypepageassociationDao.checkPageAccess(usertypeId, pageId);
	}

}
