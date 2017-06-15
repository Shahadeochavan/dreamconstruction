package com.nextech.erp.daoImpl;


import org.hibernate.Criteria;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nextech.erp.dao.QualitycheckrawmaterialDao;
import com.nextech.erp.model.Qualitycheckrawmaterial;

@Repository
@Transactional
public class QualitycheckrawmaterialDaoImpl extends
		SuperDaoImpl<Qualitycheckrawmaterial> implements
		QualitycheckrawmaterialDao {

	@Override
	public Qualitycheckrawmaterial getQualitycheckrawmaterialByInvoiceIdAndRMId(long invoiceId,long rmId) throws Exception {
		session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Qualitycheckrawmaterial.class);
		criteria.add(Restrictions.eq("isactive", true));
		criteria.add(Restrictions.eq("rawmaterialorderinvoice.id", invoiceId));
		criteria.add(Restrictions.eq("rawmaterial.id", rmId));
		Qualitycheckrawmaterial qualitycheckrawmaterial = criteria.list().size() > 0 ? (Qualitycheckrawmaterial) criteria.list().get(0): null;
		return qualitycheckrawmaterial;
}
}
