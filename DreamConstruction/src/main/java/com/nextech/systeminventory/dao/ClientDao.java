package com.nextech.systeminventory.dao;

import com.nextech.systeminventory.model.Client;

public interface ClientDao extends SuperDao<Client>{

	public Client getClientByCompanyName(String companyName) throws Exception;

	public Client getClientByEmail(String email) throws Exception;
}
