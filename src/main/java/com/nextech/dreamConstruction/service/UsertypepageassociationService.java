package com.nextech.dreamConstruction.service;

import java.util.List;

import com.nextech.dreamConstruction.dto.UserTypePageAssoDTO;
import com.nextech.dreamConstruction.model.Usertypepageassociation;

public interface UsertypepageassociationService extends CRUDService<Usertypepageassociation>{
	
	public List<UserTypePageAssoDTO> getPagesByUsertype(long usertypeId);
	
	public boolean checkPageAccess(long usertypeId,long pageId);
	
	public UserTypePageAssoDTO addMultipleUserTypePageAsso(UserTypePageAssoDTO userTypePageAssoDTO,String user) throws Exception;
	
	public Usertypepageassociation getUserTypePageAssoByPageIduserTypeId(long pageId,long userTypeId) throws Exception; 
}
