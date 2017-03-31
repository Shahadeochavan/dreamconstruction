package com.nextech.erp.daoImpl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import com.nextech.erp.dao.ProductinventoryDao;
import com.nextech.erp.model.Productinventory;

public class ProductinventoryDaoImpl extends SuperDaoImpl<Productinventory>
		implements ProductinventoryDao {

	@Override
	public Productinventory getProductinventoryByProductId(long productId)
			throws Exception {
		session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Productinventory.class);
		criteria.add(Restrictions.eq("product.id", productId));
		criteria.add(Restrictions.eq("isactive", true));
		Productinventory productinventory = (Productinventory) (criteria.list()
				.size() > 0 ? criteria.list().get(0) : null);
		return productinventory;
	}
}
