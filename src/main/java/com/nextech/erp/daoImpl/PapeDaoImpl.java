package com.nextech.erp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.erp.dao.PageDao;
import com.nextech.erp.model.Page;
import com.nextech.erp.model.Usertypepageassociation;

public class PapeDaoImpl implements PageDao{

	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addPage(Page page) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.save(page);
		tx.commit();
		session.close();
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page getPageById(long id) throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Page.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("id", id));
		Page page =criteria.list().size() > 0 ? (Page) criteria.list().get(0)
				: null;
		session.close();
		return page;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Page> getPageist() throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Page.class);
		criteria.add(Restrictions.eq("isactive", true));
		List<Page> pageList = criteria.list();
		session.close();
		return pageList;
	}

	@Override
	public boolean deletePage(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Page.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}

	@Override
	public Page updatePage(Page page) throws Exception {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(page);
		session.getTransaction().commit();
		session.close();
		return page;
	}
}

