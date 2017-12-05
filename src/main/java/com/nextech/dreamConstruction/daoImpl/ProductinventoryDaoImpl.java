package com.nextech.dreamConstruction.daoImpl;

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

import com.nextech.dreamConstruction.dao.ProductinventoryDao;
import com.nextech.dreamConstruction.model.Productinventory;

@Repository
@Transactional
public class ProductinventoryDaoImpl extends SuperDaoImpl<Productinventory>
		implements ProductinventoryDao {

	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;
	@Override
	public Productinventory getProductinventoryByProductId(long productId)
			throws Exception {
		session = sessionFactory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Productinventory> criteria = builder.createQuery(Productinventory.class);
		Root<Productinventory> userRoot = (Root<Productinventory>) criteria.from(Productinventory.class);
		criteria.select(userRoot).where(builder.equal(userRoot.get("product"), productId),builder.equal(userRoot.get("isactive"), true));
		TypedQuery<Productinventory> query = session.createQuery(criteria);
		  List<Productinventory> list = query.getResultList();
		  if (list.isEmpty()) {
		        return null;
		    }
		    return list.get(0);
	}

	@Override
	public List<Productinventory> getProductinventoryListByProductId(
			long productId) throws Exception {
		session = sessionFactory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Productinventory> criteria = builder.createQuery(Productinventory.class);
		Root<Productinventory> userRoot  = (Root<Productinventory>) criteria.from(Productinventory.class);
		criteria.select(userRoot).where(builder.equal(userRoot.get("product"), productId),builder.equal(userRoot.get("isactive"), true));
		TypedQuery<Productinventory> query = session.createQuery(criteria);
		return query.getResultList();
	}
}
