package com.nextech.dreamConstruction.daoImpl;

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

import com.nextech.dreamConstruction.dao.PurchaseAssnDao;
import com.nextech.dreamConstruction.model.Productorderassociation;
import com.nextech.dreamConstruction.model.PurchaseAssn;

@Repository
@Transactional
public class PurchaseAssnDaoImpl extends SuperDaoImpl<PurchaseAssn> implements PurchaseAssnDao{

	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;
	
	@Override
	public List<PurchaseAssn> getPurchaseAssnByPurchaseId(long purhaseId)
			throws Exception {
		session = sessionFactory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<PurchaseAssn> criteria = builder.createQuery(PurchaseAssn.class);
		Root<PurchaseAssn> userRoot  = (Root<PurchaseAssn>) criteria.from(PurchaseAssn.class);
		criteria.select(userRoot).where(builder.equal(userRoot.get("purchase"), purhaseId),builder.equal(userRoot.get("isactive"), true));
		TypedQuery<PurchaseAssn> query = session.createQuery(criteria);
		return query.getResultList();
	}

	@Override
	public PurchaseAssn getPurchaseAssnByPurchaseIdAndProductId(
			long purchaseId, long productId) throws Exception {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<PurchaseAssn> criteria = builder.createQuery(PurchaseAssn.class);
		Root<PurchaseAssn> userRoot = (Root<PurchaseAssn>) criteria.from(PurchaseAssn.class);
		criteria.select(userRoot).where(builder.equal(userRoot.get("purchase"), purchaseId),builder.equal(userRoot.get("product"), productId),builder.equal(userRoot.get("isactive"), true));
		TypedQuery<PurchaseAssn> query = session.createQuery(criteria);
		  List<PurchaseAssn> list = query.getResultList();
		  if (list.isEmpty()) {
		        return null;
		    }
		    return list.get(0);
	}
}
