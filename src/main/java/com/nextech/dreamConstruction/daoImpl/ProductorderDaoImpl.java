package com.nextech.dreamConstruction.daoImpl;

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

import com.nextech.dreamConstruction.dao.ProductorderDao;
import com.nextech.dreamConstruction.model.Productorder;

@Repository
@Transactional
public class ProductorderDaoImpl extends SuperDaoImpl<Productorder> implements ProductorderDao {

	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;
	@Override
	public Productorder getProductorderByProductOrderId(long pOrderId)throws Exception {
		session = sessionFactory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Productorder> criteria = builder.createQuery(Productorder.class);
		Root<Productorder> userRoot = (Root<Productorder>) criteria.from(Productorder.class);
		criteria.select(userRoot).where(builder.equal(userRoot.get("id"), pOrderId),builder.equal(userRoot.get("isactive"), true));
		TypedQuery<Productorder> query = session.createQuery(criteria);
		  List<Productorder> list = query.getResultList();
		  if (list.isEmpty()) {
		        return null;
		    }
		    return list.get(0);
	}
	
	@Override
	public List<Productorder> getPendingProductOrders(long statusId,long statusId1) {
		session = sessionFactory.openSession();
	
		  CriteriaBuilder criteriaBuilder=session.getCriteriaBuilder();
		    CriteriaQuery<Productorder> criteriaQuery=criteriaBuilder.createQuery(Productorder.class);
		    Metamodel metamodel=session.getMetamodel();
		    EntityType<Productorder> entityType = metamodel.entity(Productorder.class);
		    Root<Productorder> root = criteriaQuery.from(entityType);
		    criteriaQuery.where(root.get("status").in(statusId,statusId1));
		    TypedQuery<Productorder> typedQuery = session.createQuery(criteriaQuery);
		    return typedQuery.getResultList();
	}

	@Override
	public List<Productorder> getInCompleteProductOrder(long clientId,long statusId,long statusId1) {
		
		session = sessionFactory.openSession();
		CriteriaBuilder criteriaBuilder=session.getCriteriaBuilder();
	    CriteriaQuery<Productorder> criteriaQuery=criteriaBuilder.createQuery(Productorder.class);
	    Metamodel metamodel=session.getMetamodel();
	    EntityType<Productorder> entityType = metamodel.entity(Productorder.class);
	    Root<Productorder> root = criteriaQuery.from(entityType);
	    criteriaQuery.where(root.get("client").in(clientId,statusId,statusId1));
	    TypedQuery<Productorder> typedQuery = session.createQuery(criteriaQuery);
	    return typedQuery.getResultList();
	}

	@Override
	public List<Productorder> getProductPrderByClientId(long clientId)
			throws Exception {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Productorder> criteria = builder.createQuery(Productorder.class);
		Root<Productorder> userRoot = (Root<Productorder>) criteria.from(Productorder.class);
		criteria.select(userRoot).where(builder.equal(userRoot.get("client"), clientId),builder.equal(userRoot.get("isactive"), true));
		TypedQuery<Productorder> query = session.createQuery(criteria);
		  return  query.getResultList();
	}

}