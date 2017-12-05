package com.nextech.dreamConstruction.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextech.dreamConstruction.dao.StatusDao;
import com.nextech.dreamConstruction.dto.StatusDTO;
import com.nextech.dreamConstruction.factory.StatusRequestResponseFactory;
import com.nextech.dreamConstruction.model.Status;
import com.nextech.dreamConstruction.service.StatusService;

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
