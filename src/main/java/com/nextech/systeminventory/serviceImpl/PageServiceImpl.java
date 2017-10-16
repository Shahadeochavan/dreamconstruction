package com.nextech.systeminventory.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextech.systeminventory.dao.PageDao;
import com.nextech.systeminventory.model.Page;
import com.nextech.systeminventory.service.PageService;
@Service
public class PageServiceImpl extends CRUDServiceImpl<Page> implements PageService{

	@Autowired
	PageDao pageDao;
	
	@Override
	public Page getPageByUrl(String url) throws Exception {
		// TODO Auto-generated method stub
		return pageDao.getPageByUrl(url);
	}
	
}
