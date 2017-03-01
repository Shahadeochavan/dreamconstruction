package com.nextech.erp.daoImpl;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.nextech.erp.dao.RawmaterialorderDao;
import com.nextech.erp.model.Rawmaterialorder;

public class RawmaterialorderDaoImpl extends SuperDaoImpl<Rawmaterialorder>
		implements RawmaterialorderDao {

	@Override
	public Rawmaterialorder getRawmaterialorderByIdName(long id, String rmname)
			throws Exception {
		session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Rawmaterialorder.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("id", id));
		criteria.add(Restrictions.eq("name", rmname));
		Rawmaterialorder rawmaterialorder = criteria.list().size() > 0 ? (Rawmaterialorder) criteria
				.list().get(0) : null;
		 //session.close();
		return rawmaterialorder;
	}

	@Override
	public List<Rawmaterialorder> getRawmaterialorderByStatusId(long statusId,long statusId1,long statusId2)
			throws Exception {
		session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Rawmaterialorder.class);
		criteria.add(Restrictions.eq("isactive", true));
		Criterion criterion = Restrictions.in("status.id", Arrays.asList(statusId,statusId1,statusId2));
		/*criteria.add(Restrictions.or(Restrictions.eq("status.id", statusId)));
		criteria.add(Restrictions.eq("status.id", statusId1));
		criteria.add(Restrictions.eq("status.id", statusId2));*/
		criteria.add(Restrictions.and(criterion));
		@SuppressWarnings("unchecked")
		List<Rawmaterialorder> rawmaterialorder = criteria.list().size() > 0 ? (List<Rawmaterialorder>) criteria
				.list() : null;
		 session.close();
		return rawmaterialorder;
	}

	@Override
	public List<Rawmaterialorder> getRawmaterialorderByQualityCheckStatusId(
			long statusId) throws Exception {
		session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Rawmaterialorder.class);
	/*	Criterion statusId1= Restrictions.eq("1", statusId);
		Criterion statusId2=Restrictions.eq("2", statusId);
		Criterion statusId3=Restrictions.eq("11", statusId);
		Criterion statusId4=Restrictions.eq(propertyName, value)*/
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("status.id", statusId));
		
		@SuppressWarnings("unchecked")
		List<Rawmaterialorder> rawmaterialorder = criteria.list().size() > 0 ? (List<Rawmaterialorder>) criteria
				.list() : null;
		 session.close();
		return rawmaterialorder;

	}
}
