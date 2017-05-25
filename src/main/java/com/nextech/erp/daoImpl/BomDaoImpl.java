package com.nextech.erp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.nextech.erp.dao.BomDao;
import com.nextech.erp.model.Bom;

public class BomDaoImpl extends SuperDaoImpl<Bom> implements BomDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Bom> getBomListByProductId(long productID) throws Exception {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Bom.class);
		criteria.add(Restrictions.eq("product.id", productID));
		criteria.add(Restrictions.eq("isactive", true));
		return (List<Bom>) (criteria.list().size() > 0 ? criteria.list() : null);
	}

}
