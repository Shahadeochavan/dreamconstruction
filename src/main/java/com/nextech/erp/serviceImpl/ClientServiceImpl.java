package com.nextech.erp.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.ClientDao;
import com.nextech.erp.model.Client;
import com.nextech.erp.service.ClientService;

public class ClientServiceImpl implements ClientService{

	@Autowired
	ClientDao clientDao;

	@Override
	public boolean addClient(Client client) throws Exception {
		client.setCreatedDate(new Timestamp(new Date().getTime()));
		return clientDao.addClient(client);
	}

	@Override
	public Client getClientById(long id) throws Exception {
		return clientDao.getClientById(id);
	}

	@Override
	public List<Client> getClientList() throws Exception {
		return clientDao.getClientList();
	}

	@Override
	public boolean deleteClient(long id) throws Exception {
		return clientDao.deleteClient(id);
	}

	@Override
	public Client updateClient(Client client) throws Exception {
		client.setUpdatedDate(new Timestamp(new Date().getTime()));
		return clientDao.updateClient(client);
	}

}

