package com.nextech.erp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.nextech.erp.dao.BOMRMVendorAssociationDao;
import com.nextech.erp.model.BOMRMVendorAssociation;

public class BOMRMVendorAssociationDaoImpl extends
		SuperDaoImpl<BOMRMVendorAssociation> implements
		BOMRMVendorAssociationDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<BOMRMVendorAssociation> getBomRMVendorByBomId(long bomId)
			throws Exception {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session
				.createCriteria(BOMRMVendorAssociation.class);
		criteria.add(Restrictions.eq("bom.id", bomId));
		criteria.add(Restrictions.eq("isactive", true));
		return (List<BOMRMVendorAssociation>) (criteria.list().size() > 0 ? criteria
				.list() : null);
	}
}
