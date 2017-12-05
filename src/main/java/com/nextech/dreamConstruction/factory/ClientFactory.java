package com.nextech.dreamConstruction.factory;


import com.nextech.dreamConstruction.dto.ClientDTO;
import com.nextech.dreamConstruction.model.Client;

public class ClientFactory {

	public static Client setClient( ClientDTO clientDTO){
		Client client = new Client();
		client.setAddress(clientDTO.getAddress());
		client.setCommisionerate(clientDTO.getCommisionerate());
		client.setCompanyname(clientDTO.getCompanyName());
		client.setContactnumber(clientDTO.getContactNumber());
		client.setContactpersonname(clientDTO.getContactPersonName());
		client.setCst(clientDTO.getCst());
		client.setCustomerEccNumber(clientDTO.getCustomerEccNumber());
		client.setDescription(clientDTO.getDescription());
		client.setDivision(clientDTO.getDivision());
		client.setEmailid(clientDTO.getEmailId());
		client.setRenge(clientDTO.getRenge());
		client.setVatNo(clientDTO.getVatNo());
		client.setIsactive(true);
		return client;
	}
	
	public static Client setClientUpdate( ClientDTO clientDTO){
		Client client = new Client();
		client.setId(clientDTO.getId());
		client.setAddress(clientDTO.getAddress());
		client.setCommisionerate(clientDTO.getCommisionerate());
		client.setCompanyname(clientDTO.getCompanyName());
		client.setContactnumber(clientDTO.getContactNumber());
		client.setContactpersonname(clientDTO.getContactPersonName());
		client.setCst(clientDTO.getCst());
		client.setCustomerEccNumber(clientDTO.getCustomerEccNumber());
		client.setDescription(clientDTO.getDescription());
		client.setDivision(clientDTO.getDivision());
		client.setEmailid(clientDTO.getEmailId());
		client.setRenge(clientDTO.getRenge());
		client.setVatNo(clientDTO.getVatNo());
		client.setIsactive(true);
		return client;
	}
	
	public static ClientDTO setClientDTO(Client  client){
		ClientDTO clientDTO =  new ClientDTO();
		clientDTO.setId(client.getId());
		clientDTO.setAddress(client.getAddress());
		clientDTO.setCommisionerate(client.getCommisionerate());
		clientDTO.setCompanyName(client.getCompanyname());
		clientDTO.setContactNumber(client.getContactnumber());
		clientDTO.setContactPersonName(client.getContactpersonname());
		clientDTO.setCst(client.getCst());
		clientDTO.setCustomerEccNumber(client.getCustomerEccNumber());
		clientDTO.setDescription(client.getDescription());
		clientDTO.setDivision(client.getDivision());
		clientDTO.setEmailId(client.getEmailid());
		clientDTO.setRenge(client.getRenge());
		clientDTO.setVatNo(client.getVatNo());
		clientDTO.setActive(true);
		return clientDTO;
	}
}
