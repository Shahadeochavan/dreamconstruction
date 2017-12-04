package com.nextech.systeminventory.service;

import com.nextech.systeminventory.dto.PageDTO;
import com.nextech.systeminventory.model.Page;

public interface PageService extends CRUDService<Page>{
	
	public Page getPageByUrl(String url) throws Exception;
	
	public PageDTO getPageDTOById(long id) throws Exception;
	
}
