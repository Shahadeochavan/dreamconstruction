package com.nextech.systeminventory.factory;

import com.nextech.systeminventory.dto.PrVndrAssnDTO;
import com.nextech.systeminventory.model.PrVndrAssn;

public class PrVndrAssnRequestResponseFactory {

	public static PrVndrAssn  setPrVndrAssn(PrVndrAssnDTO prVndrAssnDTO){
		PrVndrAssn prVndrAssn =  new PrVndrAssn();
		prVndrAssn.setVendor(prVndrAssnDTO.getVendor());
		prVndrAssn.setProduct(prVndrAssnDTO.getProduct());
		prVndrAssn.setIsactive(true);
		return prVndrAssn;
	}
}
