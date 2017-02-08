package com.nextech.erp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.RawmaterialorderassociationDao;
import com.nextech.erp.model.Rawmaterialorderassociation;

public class RawmaterialorderassociationDaoImpl extends SuperDaoImpl<Rawmaterialorderassociation> implements
		RawmaterialorderassociationDao {
	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Rawmaterialorderassociation> getRMOrderRMAssociationByRMOrderId(long id) throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Rawmaterialorderassociation.class);
		criteria.add(Restrictions.eq("rawmaterialorder.id", id));
		return criteria.list();
	}
}
