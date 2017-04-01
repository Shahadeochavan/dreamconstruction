package com.nextech.erp.daoImpl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.ProductRMAssoDao;
import com.nextech.erp.model.Productrawmaterialassociation;

public class ProductRMAssoDaoImpl extends SuperDaoImpl<Productrawmaterialassociation> implements ProductRMAssoDao {

	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	@Override
	public Productrawmaterialassociation getPRMAssociationByPidRmid(long pid, long rmid) throws Exception{
 		session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Productrawmaterialassociation.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("product.id", pid));
		criteria.add(Restrictions.eq("rawmaterial.id",rmid));
		Productrawmaterialassociation Productrawmaterialassociation = criteria.list().size() > 0 ? (Productrawmaterialassociation) criteria.list().get(0) : null;
		return Productrawmaterialassociation;
	}
}

