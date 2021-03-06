package com.nextech.dreamConstruction.factory;

import javax.servlet.http.HttpServletRequest;

import com.nextech.dreamConstruction.dto.ContractorDTO;
import com.nextech.dreamConstruction.model.Contractor;

public class ContractorRequestResponseFactory {
	
	public static Contractor setContractor(ContractorDTO contractorDTO,HttpServletRequest request){
		Contractor contractor = new Contractor();
		contractor.setAddress(contractorDTO.getAddress());
		contractor.setEmailId(contractorDTO.getEmailId());
		contractor.setFirstName(contractorDTO.getFirstName());
		contractor.setLastName(contractorDTO.getLastName());
		contractor.setMobileNumber(contractorDTO.getMobileNumber());
		contractor.setCreatedBy((request.getAttribute("current_user").toString()));
		contractor.setIsactive(true);
		return  contractor;
	}
	
	public static Contractor setContractorUpdate(ContractorDTO contractorDTO,HttpServletRequest request){
		Contractor contractor = new Contractor();
		contractor.setAddress(contractorDTO.getAddress());
		contractor.setEmailId(contractorDTO.getEmailId());
		contractor.setFirstName(contractorDTO.getFirstName());
		contractor.setLastName(contractorDTO.getLastName());
		contractor.setMobileNumber(contractorDTO.getMobileNumber());
		contractor.setUpdatedBy((request.getAttribute("current_user").toString()));
		contractor.setIsactive(true);
		return  contractor;
	}

}
