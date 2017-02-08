package com.nextech.erp.service;

import java.util.List;

import com.nextech.erp.model.Rawmaterial;

public interface RawmaterialService extends CRUDService<Rawmaterial>{
	
	List<Rawmaterial> getRawMaterialByRMOrderId(Long id) throws Exception;
}