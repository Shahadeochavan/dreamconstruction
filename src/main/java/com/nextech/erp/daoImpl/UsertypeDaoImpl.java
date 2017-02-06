package com.nextech.erp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.UserTypeDao;
import com.nextech.erp.model.Usertype;

public class UsertypeDaoImpl implements UserTypeDao {
	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	@Override
	public Long addUsertype(Usertype userType) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		Long id = (Long) session.save(userType);
		tx.commit();
		session.close();
		return id;
	}
	@SuppressWarnings("deprecation")
	@Override
	public Usertype getUsertypeById(long id) throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Usertype.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("id", id));
		Usertype usertype= criteria.list().size() > 0 ? (Usertype) criteria.list().get(0)
				: null;
		session.close();
		return usertype;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Usertype> getUsertypeList() throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Usertype.class);
		criteria.add(Restrictions.eq("isactive", true));
		List<Usertype> userList = criteria.list();
		session.close();
		return userList;
	}

	@Override
	public boolean deleteUsertype(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Usertype.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}

	@Override
	public Usertype updateUsertype(Usertype userType) throws Exception {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(userType);
		session.getTransaction().commit();
		session.close();
		return userType;
	}
}

