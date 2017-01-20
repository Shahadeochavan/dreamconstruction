package com.nextech.erp.dao;

import java.util.List;
import com.nextech.erp.model.Usertype;

public interface UserTypeDao {
	public boolean addUsertype(Usertype user) throws Exception;

	public Usertype getUsertypeById(long id) throws Exception;

	public List<Usertype> getUsertypeList() throws Exception;

	public boolean deleteUsertype(long id) throws Exception;

	public Usertype updateUsertype(Usertype user) throws Exception;
}
