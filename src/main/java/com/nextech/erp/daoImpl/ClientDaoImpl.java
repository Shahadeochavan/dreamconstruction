package com.nextech.erp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.ClientDao;
import com.nextech.erp.model.Client;

public class ClientDaoImpl implements ClientDao {
	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addClient(Client client) {
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(client);
			tx.commit();
			session.close();
		} catch (ConstraintViolationException cve) {
			System.out.println("Inside addClient");
			cve.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Client getClientById(long id) throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Client.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("id", id));
		Client client = criteria.list().size() > 0 ? (Client) criteria.list()
				.get(0) : null;
		session.close();
		return client;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Client> getClientList() throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Client.class);
		criteria.add(Restrictions.eq("isactive", true));
		List<Client> clientList = criteria.list();
		session.close();
		return clientList;
	}

	@Override
	public boolean deleteClient(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Client.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}

	@Override
	public Client updateClient(Client client) throws Exception {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(client);
		session.getTransaction().commit();
		session.close();
		return client;
	}
}
