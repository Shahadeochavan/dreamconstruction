package com.nextech.erp.daoImpl;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import com.nextech.erp.dao.ProductionplanningDao;
import com.nextech.erp.model.Productionplanning;

public class ProductionplanningDaoImpl extends SuperDaoImpl<Productionplanning> implements ProductionplanningDao  {

	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;
	@Override
	public Productionplanning getProductionPlanningforCurrentMonthByProductIdAndDate(
			long pId,Date date) throws Exception {
		session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Productionplanning.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("product.id", pId));
		criteria.add(Restrictions.eq("date",date));
		Productionplanning productionplanning= criteria.list().size() > 0 ? (Productionplanning) criteria.list()
				.get(0) : null;
		 session.close();
		return productionplanning;
	}

}
