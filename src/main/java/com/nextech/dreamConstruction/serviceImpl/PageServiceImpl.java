package com.nextech.dreamConstruction.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextech.dreamConstruction.dao.PageDao;
import com.nextech.dreamConstruction.dto.PageDTO;
import com.nextech.dreamConstruction.factory.PageFactory;
import com.nextech.dreamConstruction.model.Page;
import com.nextech.dreamConstruction.service.PageService;
@Service
public class PageServiceImpl extends CRUDServiceImpl<Page> implements PageService{

	@Autowired
	PageDao pageDao;
	
	@Override
	public Page getPageByUrl(String url) throws Exception {
		// TODO Auto-generated method stub
		return pageDao.getPageByUrl(url);
	}
	
	@Override
	public PageDTO getPageDTOById(long id) throws Exception {
		Page page = pageDao.getById(Page.class, id);
		if(page==null){
			return null;
		}
		PageDTO pageDTO = PageFactory.setPageList(page);
		return pageDTO;
	}
	
}
