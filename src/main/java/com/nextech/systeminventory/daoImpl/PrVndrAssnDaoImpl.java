package com.nextech.systeminventory.daoImpl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nextech.systeminventory.dao.PrVndrAssnDao;
import com.nextech.systeminventory.model.PrVndrAssn;

@Repository
@Transactional
public class PrVndrAssnDaoImpl extends SuperDaoImpl<PrVndrAssn> implements PrVndrAssnDao{

	@Override
	public List<PrVndrAssn> getPrVndrAssnByVendorId(long vendorId)
			throws Exception {
		session = sessionFactory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<PrVndrAssn> criteria = builder.createQuery(PrVndrAssn.class);
		Root<PrVndrAssn> userRoot = (Root<PrVndrAssn>) criteria.from(PrVndrAssn.class);
		criteria.select(userRoot).where(builder.equal(userRoot.get("vendor"), vendorId),builder.equal(userRoot.get("isactive"), true));
		TypedQuery<PrVndrAssn> query = session.createQuery(criteria);
		  return  query.getResultList();
	}

	@Override
	public PrVndrAssn getPrVndrAssnByVendorIdProductId(long vendorId,
			long productId) throws Exception {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<PrVndrAssn> criteria = builder.createQuery(PrVndrAssn.class);
		Root<PrVndrAssn> userRoot = criteria.from(PrVndrAssn.class);
		criteria.select(userRoot).where(builder.equal(userRoot.get("vendor"), vendorId),builder.equal(userRoot.get("product"), productId),builder.equal(userRoot.get("isactive"), true));
		TypedQuery<PrVndrAssn> query = session.createQuery(criteria);
		List<PrVndrAssn> results = query.getResultList();
		  if (results.isEmpty()) {
		        return null;
		    }
		    return results.get(0);
	}

}
