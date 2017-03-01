package com.nextech.erp.daoImpl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.nextech.erp.dao.RawmaterialinventoryDao;
import com.nextech.erp.model.Rawmaterialinventory;

public class RawmaterialinventoryDaoImpl extends SuperDaoImpl<Rawmaterialinventory>implements RawmaterialinventoryDao {
	@Override
	public Rawmaterialinventory getByRMId(long id) throws Exception {
		session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Rawmaterialinventory.class);
		criteria.add(Restrictions.eq("rawmaterial.id",id));
		Rawmaterialinventory rawmaterialinventory = criteria.list().size() > 0 ? (Rawmaterialinventory) criteria.list().get(0) : null;
		 session.close();
		return rawmaterialinventory;
	}

	
}
