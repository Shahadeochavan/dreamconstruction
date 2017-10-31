package com.nextech.systeminventory.daoImpl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nextech.systeminventory.dao.UsertypepageassociationDao;
import com.nextech.systeminventory.model.Usertypepageassociation;

@Repository
@Transactional
public class UsertypepageassociationDaoImpl extends SuperDaoImpl<Usertypepageassociation> implements UsertypepageassociationDao {

	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;
	
	@Override
	public List<Usertypepageassociation> getPagesByUsertype(long usertypeId) {
		session = sessionFactory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Usertypepageassociation> criteria = builder.createQuery(Usertypepageassociation.class);
		Root<Usertypepageassociation> userRoot  = (Root<Usertypepageassociation>) criteria.from(Usertypepageassociation.class);
		criteria.select(userRoot).where(builder.equal(userRoot.get("usertype"), usertypeId),builder.equal(userRoot.get("isactive"), true));
		TypedQuery<Usertypepageassociation> query = session.createQuery(criteria);
		return query.getResultList();
		
	}

	@Override
	public boolean checkPageAccess(long usertypeId, long pageId) {
		
		session = sessionFactory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Usertypepageassociation> criteria = builder.createQuery(Usertypepageassociation.class);
		Root<Usertypepageassociation> userRoot = criteria.from(Usertypepageassociation.class);
		criteria.select(userRoot).where(builder.equal(userRoot.get("page"), pageId),builder.equal(userRoot.get("usertype"), usertypeId),builder.equal(userRoot.get("isactive"), true));
		TypedQuery<Usertypepageassociation> query = session.createQuery(criteria);
		List<Usertypepageassociation> results = query.getResultList();
		  if (results.isEmpty()) {
		        return false;
		    }
		    return true;
	}
	
}