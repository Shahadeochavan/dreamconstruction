package com.nextech.dreamConstruction.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextech.dreamConstruction.dao.ClientDao;
import com.nextech.dreamConstruction.dto.ClientDTO;
import com.nextech.dreamConstruction.factory.ClientFactory;
import com.nextech.dreamConstruction.model.Client;
import com.nextech.dreamConstruction.service.ClientService;
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

	@Override
	public ClientDTO getClientDTOById(long id) throws Exception {
		// TODO Auto-generated method stub
		Client client = clientDao.getById(Client.class, id);
		if(client ==null){
			return null;
		}
		ClientDTO clientDTO = ClientFactory.setClientDTO(client);
		return clientDTO;
	}
}

