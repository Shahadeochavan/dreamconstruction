package com.nextech.erp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.nextech.erp.dao.RawmaterialorderDao;
import com.nextech.erp.model.Rawmaterialorder;

public class RawmaterialorderDaoImpl extends SuperDaoImpl<Rawmaterialorder> implements RawmaterialorderDao{
	
	@Override
	public Rawmaterialorder getRawmaterialorderByIdName(long id,String rmname) throws Exception{
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Rawmaterialorder.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("id", id));
		criteria.add(Restrictions.eq("name", rmname));
		Rawmaterialorder rawmaterialorder = criteria.list().size() > 0 ? (Rawmaterialorder) criteria.list()
				.get(0) : null;
		session.close();
		return rawmaterialorder;
	}
	@Override
	public List<Rawmaterialorder> getRawmaterialorderByStatusId(long statusId) throws Exception{
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Rawmaterialorder.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("status.id", statusId));
		@SuppressWarnings("unchecked")
		List<Rawmaterialorder> rawmaterialorder =  criteria.list().size() > 0 ? (List<Rawmaterialorder>) criteria.list(): null;
		session.close();
		return rawmaterialorder;
	}
	
}
