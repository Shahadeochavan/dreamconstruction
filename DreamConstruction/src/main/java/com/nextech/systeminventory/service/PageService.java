package com.nextech.systeminventory.service;

import com.nextech.systeminventory.model.Page;

public interface PageService extends CRUDService<Page>{
	
	public Page getPageByUrl(String url) throws Exception;
	
}
