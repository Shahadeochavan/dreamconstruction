package com.nextech.systeminventory.service;

import com.nextech.systeminventory.dto.ClientDTO;
import com.nextech.systeminventory.model.Client;

public interface ClientService extends CRUDService<Client>{

	public Client getClientByCompanyName(String companyName) throws Exception;

	public Client getClientByEmail(String email) throws Exception;
	
	public ClientDTO  getClientDTOById(long id) throws Exception;
}
