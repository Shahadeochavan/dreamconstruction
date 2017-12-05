package com.nextech.dreamConstruction.daoImpl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import com.nextech.dreamConstruction.dao.UserDao;
import com.nextech.dreamConstruction.model.User;

@Repository
@Transactional
public class UserDaoImpl extends SuperDaoImpl<User> implements UserDao {

	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;
	@Override
	public User getUserByUserId(String userid) throws Exception {
		session = sessionFactory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> userRoot = criteria.from(User.class);
		criteria.select(userRoot).where(builder.equal(userRoot.get("userid"), userid),builder.equal(userRoot.get("isactive"), true));
		TypedQuery<User> query = session.createQuery(criteria);
		List<User> results = query.getResultList();
		  if (results.isEmpty()) {
		        return null;
		    }
		    return results.get(0);
	}

	@Override
	public User getUserByEmail(String email) throws Exception {
		session = sessionFactory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> userRoot = criteria.from(User.class);
		criteria.select(userRoot).where(builder.equal(userRoot.get("email"), email),builder.equal(userRoot.get("isactive"), true));
		TypedQuery<User> query = session.createQuery(criteria);
		List<User> results = query.getResultList();
		  if (results.isEmpty()) {
		        return null;
		    }
		    return results.get(0);
	}

	@Override
	public User getUserByMobile(String mobile) throws Exception {
		session = sessionFactory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> userRoot = criteria.from(User.class);
		criteria.select(userRoot).where(builder.equal(userRoot.get("mobile"), mobile),builder.equal(userRoot.get("isactive"), true));
		TypedQuery<User> query = session.createQuery(criteria);
		List<User> results = query.getResultList();
		  if (results.isEmpty()) {
		        return null;
		    }
		    return results.get(0);
	}
	
	@Override
	public List<User> getMultipleUsersById(List<Long> ids) throws Exception {
		session = sessionFactory.openSession();
		  CriteriaBuilder criteriaBuilder=session.getCriteriaBuilder();
		    CriteriaQuery<User> criteriaQuery=criteriaBuilder.createQuery(User.class);
		    Metamodel metamodel=session.getMetamodel();
		    EntityType<User> entityType = metamodel.entity(User.class);
		    Root<User> root = criteriaQuery.from(entityType);
		    criteriaQuery.where(root.get("id").in(ids));
		    TypedQuery<User> typedQuery = session.createQuery(criteriaQuery);
		    return typedQuery.getResultList();

	}
}
