package com.nextech.erp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import com.nextech.erp.dao.ProductorderDao;
import com.nextech.erp.model.Productorder;

public class ProductorderDaoImpl extends SuperDaoImpl<Productorder> implements
		ProductorderDao {

	@Override
	public Productorder getProductorderByProductOrderId(long pOrderId)throws Exception {
		session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Productorder.class);
		criteria.add(Restrictions.eq("id", pOrderId));
		criteria.add(Restrictions.eq("isactive", true));
		Productorder productorder = (Productorder) (criteria.list().size() > 0 ? criteria.list().get(0) : null);
		return productorder;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Productorder> getPendingProductOrders(long statusId) {
		session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Productorder.class);
		criteria.add(Restrictions.eq("status.id", statusId));
		criteria.add(Restrictions.eq("isactive", true));
		return (List<Productorder>) (criteria.list().size() > 0 ? criteria.list() : null);
	}

}