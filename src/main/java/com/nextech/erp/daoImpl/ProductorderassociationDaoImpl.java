package com.nextech.erp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.ProductorderassociationDao;
import com.nextech.erp.model.Productorderassociation;

public class ProductorderassociationDaoImpl implements ProductorderassociationDao{
	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addProductorderassociation(Productorderassociation productorderassociation) {
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(productorderassociation);
			tx.commit();
			session.close();
		} catch (ConstraintViolationException cve) {
			System.out.println("Inside addProductorderassociation");
			cve.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Productorderassociation getProductorderassociationById(long id) throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Productorderassociation.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("id", id));
		Productorderassociation productorderassociation = criteria.list().size() > 0 ? (Productorderassociation) criteria.list()
				.get(0) : null;
		session.close();
		return productorderassociation;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Productorderassociation> getProductorderassociationList() throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Productorderassociation.class);
		criteria.add(Restrictions.eq("isactive", true));
		List<Productorderassociation> productorderassociationList = criteria.list();
		session.close();
		return productorderassociationList;
	}

	@Override
	public boolean deleteProductorderassociation(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Productorderassociation.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}

	@Override
	public Productorderassociation updateProductorderassociation(Productorderassociation productorderassociation) throws Exception {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(productorderassociation);
		session.getTransaction().commit();
		session.close();
		return productorderassociation;
	}
}

