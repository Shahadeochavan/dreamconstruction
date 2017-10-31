package com.nextech.systeminventory.service;

import com.nextech.systeminventory.dto.StatusDTO;
import com.nextech.systeminventory.model.Status;

public interface StatusService extends CRUDService<Status>{
	
	public StatusDTO  getStatusById(long id) throws Exception;
}


