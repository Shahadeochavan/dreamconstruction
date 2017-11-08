package com.nextech.systeminventory.daoImpl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nextech.systeminventory.dao.ProductorderassociationDao;
import com.nextech.systeminventory.model.Productorderassociation;

@Repository
@Transactional
public class ProductorderassociationDaoImpl extends SuperDaoImpl<Productorderassociation> implements ProductorderassociationDao {

	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;
	@Override
	public Productorderassociation getProductorderassociationByProdcutOrderIdandProdcutId(
			long pOrderId, long pId) throws Exception {
		session = sessionFactory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Productorderassociation> criteria = builder.createQuery(Productorderassociation.class);
		Root<Productorderassociation> userRoot = (Root<Productorderassociation>) criteria.from(Productorderassociation.class);
		criteria.select(userRoot).where(builder.equal(userRoot.get("product"), pId),builder.equal(userRoot.get("productorder"), pOrderId),builder.equal(userRoot.get("isactive"), true));
		TypedQuery<Productorderassociation> query = session.createQuery(criteria);
		  List<Productorderassociation> list = query.getResultList();
		  if (list.isEmpty()) {
		        return null;
		    }
		    return list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Productorderassociation> getProductorderassociationByProdcutId(long pId) throws Exception {
		session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Productorderassociation.class);
		criteria.add(Restrictions.eq("product.id", pId));
		criteria.add(Restrictions.ge("remainingQuantity", new Long(0)));
		criteria.add(Restrictions.eq("isactive", true));
		return (criteria.list().size() > 0 ? (List<Productorderassociation>)criteria.list() : null);
	}

	@Override
	public List<Productorderassociation> getProductorderassociationByOrderId(
			long oderID) throws Exception {
		session = sessionFactory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Productorderassociation> criteria = builder.createQuery(Productorderassociation.class);
		Root<Productorderassociation> userRoot  = (Root<Productorderassociation>) criteria.from(Productorderassociation.class);
		criteria.select(userRoot).where(builder.equal(userRoot.get("productorder"), oderID),builder.equal(userRoot.get("isactive"), true));
		TypedQuery<Productorderassociation> query = session.createQuery(criteria);
		return query.getResultList();
	}

	@Override
	public List<Productorderassociation> getIncompleteProductOrderAssoByProdutId(long productId)
			throws Exception {
		session = sessionFactory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Productorderassociation> criteria = builder.createQuery(Productorderassociation.class);
		Root<Productorderassociation> userRoot = (Root<Productorderassociation>) criteria.from(Productorderassociation.class);
		criteria.select(userRoot).where(builder.notEqual(userRoot.get("remainingQuantity"), 0),builder.equal(userRoot.get("isactive"), true));
		TypedQuery<Productorderassociation> query = session.createQuery(criteria);
		List<Productorderassociation> list = query.getResultList();
		return list.isEmpty() ? null : list;
	}

	@Override
	public List<Productorderassociation> getProductOrderAssoByOrderId(
			long orderId) throws Exception {
		session = sessionFactory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Productorderassociation> criteria = builder.createQuery(Productorderassociation.class);
		Root<Productorderassociation> userRoot  = (Root<Productorderassociation>) criteria.from(Productorderassociation.class);
		criteria.select(userRoot).where(builder.equal(userRoot.get("productorder"), orderId),builder.equal(userRoot.get("isactive"), true));
		TypedQuery<Productorderassociation> query = session.createQuery(criteria);
		return query.getResultList();
	}

	@Override
	public Productorderassociation getProdcutAssoByProdcutId(long prodcutId)
			throws Exception {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Productorderassociation> criteria = builder.createQuery(Productorderassociation.class);
		Root<Productorderassociation> userRoot = (Root<Productorderassociation>) criteria.from(Productorderassociation.class);
		criteria.select(userRoot).where(builder.equal(userRoot.get("product"), prodcutId),builder.equal(userRoot.get("isactive"), true));
		TypedQuery<Productorderassociation> query = session.createQuery(criteria);
		  List<Productorderassociation> list = query.getResultList();
		  if (list.isEmpty()) {
		        return null;
		    }
		    return list.get(0);
	}
}
