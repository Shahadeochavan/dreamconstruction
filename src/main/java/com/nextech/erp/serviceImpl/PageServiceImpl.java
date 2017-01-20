package com.nextech.erp.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.nextech.erp.dao.PageDao;
import com.nextech.erp.model.Page;
import com.nextech.erp.service.PageService;

public class PageServiceImpl implements PageService{
	
	@Autowired
	PageDao pagedao;

	@Override
	public boolean addPage(Page page) throws Exception {
		page.setCreatedDate(new Timestamp(new Date().getTime()));
		return pagedao.addPage(page);
	}

	@Override
	public Page getPageById(long id) throws Exception {
		return pagedao.getPageById(id);
	}

	@Override
	public List<Page> getPageist() throws Exception {
		return pagedao.getPageist();
	}

	@Override
	public boolean deletePage(long id) throws Exception {
		return pagedao.deletePage(id);
	}
	@Override
	public Page updatePage(Page page)throws Exception{
		page.setUpdatedDate(new Timestamp(new Date().getTime()));
		return pagedao.updatePage(page);
	}
}
