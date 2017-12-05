package com.nextech.dreamConstruction.service;

import com.nextech.dreamConstruction.dto.ClientDTO;
import com.nextech.dreamConstruction.model.Client;

public interface ClientService extends CRUDService<Client>{

	public Client getClientByCompanyName(String companyName) throws Exception;

	public Client getClientByEmail(String email) throws Exception;
	
	public ClientDTO  getClientDTOById(long id) throws Exception;
}
