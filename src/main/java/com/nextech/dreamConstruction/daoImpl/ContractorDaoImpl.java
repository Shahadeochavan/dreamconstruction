package com.nextech.dreamConstruction.daoImpl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nextech.dreamConstruction.dao.ContractorDao;
import com.nextech.dreamConstruction.model.Contractor;

@Repository
@Transactional
public class ContractorDaoImpl extends SuperDaoImpl<Contractor> implements ContractorDao{

}
