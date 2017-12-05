package com.nextech.dreamConstruction.dao;

import com.nextech.dreamConstruction.model.Client;

public interface ClientDao extends SuperDao<Client>{

	public Client getClientByCompanyName(String companyName) throws Exception;

	public Client getClientByEmail(String email) throws Exception;
}
