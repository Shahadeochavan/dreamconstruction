package com.nextech.systeminventory.daoImpl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nextech.systeminventory.dao.UserDao;
import com.nextech.systeminventory.model.User;

@Repository
@Transactional
public class UserDaoImpl extends SuperDaoImpl<User> implements UserDao {

	@Override
	public User getUserByUserId(String userid) throws Exception {
		session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("userid", userid));
		System.out.println("UserDaoImpl session closed session.isOpen() : " + session.isOpen() + " sessionFactory.isOpen() : " + sessionFactory.isOpen());
		User user = criteria.list().size() > 0 ? (User) criteria.list().get(0): null;
		return user;
	}

	@Override
	public User getUserByEmail(String email) throws Exception {
		session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("email", email));
		User user = criteria.list().size() > 0 ? (User) criteria.list().get(0): null;
		return user;
	}

	@Override
	public User getUserByMobile(String mobile) throws Exception {
		session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("mobile", mobile));
		User user = criteria.list().size() > 0 ? (User) criteria.list().get(0): null;
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserProfileByUserId(long id) throws Exception {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("id", id));
		return criteria.list();
	}


	@Override
	public User getUserByFirstNamLastName(String firstName, String lastName)throws Exception {
		session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("firstName", firstName));
		criteria.add(Restrictions.eq("lastName", lastName));
		System.out.println("UserDaoImpl session closed session.isOpen() : " + session.isOpen() + " sessionFactory.isOpen() : " + sessionFactory.isOpen());
		User user = criteria.list().size() > 0 ? (User) criteria.list().get(0): null;
		return user;
	}

	@Override
	public User getEmailUserById(long id) throws Exception {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("id", id));
		User user = criteria.list().size() > 0 ? (User) criteria.list().get(0): null;
		return user;
		
	}

	@Override
	public User getUserByContact(String contact) throws Exception {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("userid", contact));
		System.out.println("UserDaoImpl session closed session.isOpen() : " + session.isOpen() + " sessionFactory.isOpen() : " + sessionFactory.isOpen());
		User user = criteria.list().size() > 0 ? (User) criteria.list().get(0): null;
		return user;
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
