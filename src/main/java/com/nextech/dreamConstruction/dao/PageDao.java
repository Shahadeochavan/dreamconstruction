package com.nextech.dreamConstruction.dao;

import com.nextech.dreamConstruction.model.Page;

public interface PageDao extends SuperDao<Page>{
	
	public Page getPageByUrl(String url) throws Exception;
}
