package com.nextech.erp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.VendorDao;
import com.nextech.erp.model.Vendor;

public class VendorDaoImpl implements VendorDao {
	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addVendor(Vendor vendor) {
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(vendor);
			tx.commit();
			session.close();
		} catch (ConstraintViolationException cve) {
			System.out.println("Inside addVendor");
			cve.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Vendor getVendorById(long id) throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Vendor.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("id", id));
		Vendor vendor= criteria.list().size() > 0 ? (Vendor) criteria.list()
				.get(0) : null;
				session.close();
				return vendor;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Vendor> getVendorList() throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Vendor.class);
		criteria.add(Restrictions.eq("isactive", true));
		List<Vendor> vendorList = criteria.list();
		session.close();
		return vendorList;
	}

	@Override
	public boolean deleteVendor(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Vendor.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}

	@Override
	public Vendor updateVendor(Vendor vendor) throws Exception {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(vendor);
		session.getTransaction().commit();
		session.close();
		return vendor;
	}
	@Override
	public Vendor getVendorByCompanyName(String companyName) throws Exception{
		session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Vendor.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("companyName", companyName));
		Vendor vendor= criteria.list().size() > 0 ? (Vendor) criteria.list()
				.get(0) : null;
				session.close();
				return vendor;
	}
	@Override
	public Vendor getVendorByEmail(String email) throws Exception{
		session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Vendor.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("email", email));
		Vendor vendor= criteria.list().size() > 0 ? (Vendor) criteria.list()
				.get(0) : null;
				session.close();
				return vendor;
	}
}
