package com.nextech.systeminventory.daoImpl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nextech.systeminventory.dao.PurchaseDao;
import com.nextech.systeminventory.model.Purchase;

@Repository
@Transactional
public class PurchaseDaoImpl extends SuperDaoImpl<Purchase> implements PurchaseDao {

	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;
	
	@Override
	public List<Purchase> getPendingPurchaseOrders(long statusId, long statusId1) {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		
		  CriteriaBuilder criteriaBuilder=session.getCriteriaBuilder();
		    CriteriaQuery<Purchase> criteriaQuery=criteriaBuilder.createQuery(Purchase.class);
		    Metamodel metamodel=session.getMetamodel();
		    EntityType<Purchase> entityType = metamodel.entity(Purchase.class);
		    Root<Purchase> root = criteriaQuery.from(entityType);
		    criteriaQuery.where(root.get("status").in(statusId,statusId1));
		    TypedQuery<Purchase> typedQuery = session.createQuery(criteriaQuery);
		    return typedQuery.getResultList();
	}

}
