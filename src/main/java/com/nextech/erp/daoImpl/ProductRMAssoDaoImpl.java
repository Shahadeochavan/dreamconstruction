package com.nextech.erp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.ProductRMAssoDao;
import com.nextech.erp.model.Productrawmaterialassociation;

public class ProductRMAssoDaoImpl implements ProductRMAssoDao {

	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addProductrawmaterialassociation(Productrawmaterialassociation Productrawmaterialassociation) {
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(Productrawmaterialassociation);
			tx.commit();
			session.close();
		} catch (ConstraintViolationException cve) {
			System.out.println("Inside addProductrawmaterialassociation");
			cve.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Productrawmaterialassociation getProductrawmaterialassociationById(long id) throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Productrawmaterialassociation.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("id", id));
		Productrawmaterialassociation Productrawmaterialassociation = criteria.list().size() > 0 ? (Productrawmaterialassociation) criteria.list()
				.get(0) : null;
		session.close();
		return Productrawmaterialassociation;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Productrawmaterialassociation> getProductrawmaterialassociationList() throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Productrawmaterialassociation.class);
		criteria.add(Restrictions.eq("isactive", true));
		List<Productrawmaterialassociation> ProductrawmaterialassociationList = criteria.list();
		session.close();
		return ProductrawmaterialassociationList;
	}

	@Override
	public boolean deleteProductrawmaterialassociation(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Productrawmaterialassociation.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}

	@Override
	public Productrawmaterialassociation updateProductrawmaterialassociation(Productrawmaterialassociation Productrawmaterialassociation) throws Exception {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(Productrawmaterialassociation);
		session.getTransaction().commit();
		session.close();
		return Productrawmaterialassociation;
	}
	@Override
	public Productrawmaterialassociation getPRMAssociationByPidRmid(long pid, long rmid) throws Exception{
 		session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Productrawmaterialassociation.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("product.id", pid));
		criteria.add(Restrictions.eq("rawmaterial.id",rmid));
		Productrawmaterialassociation Productrawmaterialassociation = criteria.list().size() > 0 ? (Productrawmaterialassociation) criteria.list()
				.get(0) : null;
		session.close();
		return Productrawmaterialassociation;
	}
}

