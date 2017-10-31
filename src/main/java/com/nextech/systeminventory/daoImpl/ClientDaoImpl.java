package com.nextech.systeminventory.daoImpl;

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

import com.nextech.systeminventory.dao.ClientDao;
import com.nextech.systeminventory.model.Client;

@Repository
@Transactional
public class ClientDaoImpl extends SuperDaoImpl<Client> implements ClientDao {
	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;
	@Override
	public Client getClientByCompanyName(String companyname) throws Exception {
		
		session = sessionFactory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Client> criteria = builder.createQuery(Client.class);
		Root<Client> userRoot = (Root<Client>) criteria.from(Client.class);
		criteria.select(userRoot).where(builder.equal(userRoot.get("companyname"), companyname),builder.equal(userRoot.get("isactive"), true));
		TypedQuery<Client> query = session.createQuery(criteria);
		  List<Client> list = query.getResultList();
		  if (list.isEmpty()) {
		        return null;
		    }
		    return list.get(0);
	}

	@Override
	public Client getClientByEmail(String emailid) throws Exception {
		
		session = sessionFactory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Client> criteria = builder.createQuery(Client.class);
		Root<Client> userRoot = (Root<Client>) criteria.from(Client.class);
		criteria.select(userRoot).where(builder.equal(userRoot.get("emailid"), emailid),builder.equal(userRoot.get("isactive"), true));
		TypedQuery<Client> query = session.createQuery(criteria);
		  List<Client> list = query.getResultList();
		  if (list.isEmpty()) {
		        return null;
		    }
		    return list.get(0);
	}
}
