package com.nextech.systeminventory.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextech.systeminventory.dao.PageDao;
import com.nextech.systeminventory.dao.UserTypeDao;
import com.nextech.systeminventory.dao.UsertypepageassociationDao;
import com.nextech.systeminventory.dto.UserTypePageAssoDTO;
import com.nextech.systeminventory.dto.UserTypePageAssoPart;
import com.nextech.systeminventory.factory.UserTypePageAssoFactory;
import com.nextech.systeminventory.model.Page;
import com.nextech.systeminventory.model.Usertype;
import com.nextech.systeminventory.model.Usertypepageassociation;
import com.nextech.systeminventory.service.UsertypepageassociationService;
@Service
public class UsertypepageassociationServiceImpl extends CRUDServiceImpl<Usertypepageassociation> implements
		UsertypepageassociationService {

	@Autowired
	UsertypepageassociationDao usertypepageassociationDao;
	
	@Autowired
	UserTypeDao userTypeDao;
	
	@Autowired
	PageDao pageDao;
	
	@Override
	public List<UserTypePageAssoDTO> getPagesByUsertype(long usertypeId) {
		List<UserTypePageAssoDTO> userTypePageAssoDTOs = new ArrayList<UserTypePageAssoDTO>();
		List<Usertypepageassociation> usertypepageassociations = usertypepageassociationDao.getPagesByUsertype(usertypeId);
		for (Usertypepageassociation usertypepageassociation : usertypepageassociations) {
			UserTypePageAssoDTO userTypePageAssoDTO =  UserTypePageAssoFactory.setUserTypePageDTO(usertypepageassociation);
			userTypePageAssoDTOs.add(userTypePageAssoDTO);
		}
		return userTypePageAssoDTOs;
	}

	@Override
	public boolean checkPageAccess(long usertypeId, long pageId) {
		return usertypepageassociationDao.checkPageAccess(usertypeId, pageId);
	}
	
	@Override
	public Usertypepageassociation getUserTypePageAssoByPageIduserTypeId(
			long pageId, long userTypeId) throws Exception {
		return usertypepageassociationDao.getUserTypePageAssoByPageIduserTypeId(pageId, userTypeId);
	}
	
	@Override
	public UserTypePageAssoDTO addMultipleUserTypePageAsso(UserTypePageAssoDTO userTypePageAssoDTO, String currentUser)
			throws Exception {
		for(UserTypePageAssoPart userTypePageAssoPart : userTypePageAssoDTO.getUserTypePageAssoParts()){
			Usertypepageassociation usertypepageassociation =	 UserTypePageAssoFactory.setUserTypePageAss(userTypePageAssoDTO);
			 usertypepageassociation =  setMultiplePage(userTypePageAssoPart);
			usertypepageassociation.setUsertype(userTypeDao.getById(Usertype.class, userTypePageAssoDTO.getUsertypeId().getId()));
			usertypepageassociation.setCreatedBy(Long.parseLong(currentUser));
			usertypepageassociationDao.add(usertypepageassociation);
		}
		return userTypePageAssoDTO;
	}
	
	private Usertypepageassociation setMultiplePage(UserTypePageAssoPart userTypePageAssoPart) throws Exception {
		Usertypepageassociation usertypepageassociation = new Usertypepageassociation();
		usertypepageassociation.setPage(pageDao.getById(Page.class, userTypePageAssoPart.getPageId().getId()));
		usertypepageassociation.setIsactive(true);
		return usertypepageassociation;
	}

}
