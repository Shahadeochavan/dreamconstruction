package com.nextech.dreamConstruction.factory;

import com.nextech.dreamConstruction.dto.PrVndrAssnDTO;
import com.nextech.dreamConstruction.model.PrVndrAssn;

public class PrVndrAssnRequestResponseFactory {

	public static PrVndrAssn  setPrVndrAssn(PrVndrAssnDTO prVndrAssnDTO){
		PrVndrAssn prVndrAssn =  new PrVndrAssn();
		prVndrAssn.setVendor(prVndrAssnDTO.getVendor());
		prVndrAssn.setProduct(prVndrAssnDTO.getProduct());
		prVndrAssn.setPricePerUnit(prVndrAssnDTO.getPricePerUnit());
		prVndrAssn.setIsactive(true);
		return prVndrAssn;
	}
}
