package com.nextech.dreamConstruction.service;

import com.nextech.dreamConstruction.dto.PageDTO;
import com.nextech.dreamConstruction.model.Page;

public interface PageService extends CRUDService<Page>{
	
	public Page getPageByUrl(String url) throws Exception;
	
	public PageDTO getPageDTOById(long id) throws Exception;
	
}
