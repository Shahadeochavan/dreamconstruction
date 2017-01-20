package com.nextech.erp.dao;

import java.util.List;
import com.nextech.erp.model.Usertypepageassociation;

public interface UsertypepageassociationDao {
	public boolean addPageAss(Usertypepageassociation usertypepageassociation)
			throws Exception;

	public Usertypepageassociation getPageAssById(long id) throws Exception;

	public List<Usertypepageassociation> getPageAssList() throws Exception;

	public boolean deletePageAss(long id) throws Exception;

	public Usertypepageassociation updatePageAss(
			Usertypepageassociation usertypepageassociation) throws Exception;
}
