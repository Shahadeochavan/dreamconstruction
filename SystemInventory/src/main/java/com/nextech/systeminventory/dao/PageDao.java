package com.nextech.systeminventory.dao;

import com.nextech.systeminventory.model.Page;

public interface PageDao extends SuperDao<Page>{
	
	public Page getPageByUrl(String url) throws Exception;
}
