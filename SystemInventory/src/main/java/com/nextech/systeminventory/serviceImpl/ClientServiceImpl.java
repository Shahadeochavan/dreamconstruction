package com.nextech.systeminventory.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextech.systeminventory.dao.ClientDao;
import com.nextech.systeminventory.model.Client;
import com.nextech.systeminventory.service.ClientService;
@Service
public class ClientServiceImpl extends CRUDServiceImpl<Client> implements ClientService{

	@Autowired
	ClientDao clientDao;

	@Override
	public Client getClientByCompanyName(String companyName) throws Exception {
		return clientDao.getClientByCompanyName(companyName);
	}

	@Override
	public Client getClientByEmail(String email) throws Exception {
		return clientDao.getClientByEmail(email);
	}
}

