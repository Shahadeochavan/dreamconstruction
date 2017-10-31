package com.nextech.systeminventory.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextech.systeminventory.dao.StatusDao;
import com.nextech.systeminventory.dto.StatusDTO;
import com.nextech.systeminventory.factory.StatusRequestResponseFactory;
import com.nextech.systeminventory.model.Status;
import com.nextech.systeminventory.service.StatusService;

@Service
public class StatusServiceImpl extends CRUDServiceImpl<Status> implements StatusService {
	
	@Autowired
	StatusDao statusDao;
	@Override
	public StatusDTO getStatusById(long id) throws Exception {
		
		Status status =  statusDao.getById(Status.class, id);
		if(status==null){
			return null;
		}
		StatusDTO statusDTO = StatusRequestResponseFactory.setStatusDTO(status);
		return statusDTO;
	}
	
}
