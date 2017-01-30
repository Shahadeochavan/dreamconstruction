package com.nextech.erp.service;

import java.util.List;

import com.nextech.erp.model.Client;

public interface ClientService {
	public boolean addClient(Client Client) throws Exception;

	public Client getClientById(long id) throws Exception;

	public List<Client> getClientList() throws Exception;

	public boolean deleteClient(long id) throws Exception;

	public Client updateClient(Client Client) throws Exception;
}
