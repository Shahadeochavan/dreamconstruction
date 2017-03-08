package com.nextech.erp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.UserDao;
import com.nextech.erp.model.Rawmaterialorderassociation;
import com.nextech.erp.model.User;

public class UserDaoImpl extends SuperDaoImpl<User> implements UserDao {
	/*@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;*/

	@Override
	public User getUserByUserId(String userid) throws Exception {
		session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("userid", userid));
		System.out.println("UserDaoImpl session closed session.isOpen() : " + session.isOpen() + " sessionFactory.isOpen() : " + sessionFactory.isOpen());
		User user = criteria.list().size() > 0 ? (User) criteria.list().get(0)
				: null;
		 // //session.close();
		return user;
	}

	@Override
	public User getUserByEmail(String email) throws Exception {
		session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("email", email));
		User user = criteria.list().size() > 0 ? (User) criteria.list().get(0)
				: null;
		 // //session.close();
		return user;
	}

	@Override
	public User getUserByMobile(String mobile) throws Exception {
		session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("mobile", mobile));
		User user = criteria.list().size() > 0 ? (User) criteria.list().get(0)
				: null;
		 // //session.close();
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
	public User getUserByPassword(String password) throws Exception {
		session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("password", password));
		System.out.println("UserDaoImpl session closed session.isOpen() : " + session.isOpen() + " sessionFactory.isOpen() : " + sessionFactory.isOpen());
		User user = criteria.list().size() > 0 ? (User) criteria.list().get(0)
				: null;
		 // //session.close();
		return user;
	}
}
