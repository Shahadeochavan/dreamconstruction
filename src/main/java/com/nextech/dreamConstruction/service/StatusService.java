package com.nextech.dreamConstruction.service;

import com.nextech.dreamConstruction.dto.StatusDTO;
import com.nextech.dreamConstruction.model.Status;

public interface StatusService extends CRUDService<Status>{
	
	public StatusDTO  getStatusById(long id) throws Exception;
}


