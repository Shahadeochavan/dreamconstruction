package com.nextech.erp.daoImpl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nextech.erp.dao.ReptOptAssoDao;
import com.nextech.erp.model.Reportoutputassociation;

@Repository
@Transactional
public class ReptOptAssoDaoImpl extends SuperDaoImpl<Reportoutputassociation> implements ReptOptAssoDao {

}
