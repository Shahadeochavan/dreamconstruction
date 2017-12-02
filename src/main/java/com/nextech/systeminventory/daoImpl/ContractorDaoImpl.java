package com.nextech.systeminventory.daoImpl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nextech.systeminventory.dao.ContractorDao;
import com.nextech.systeminventory.model.Contractor;

@Repository
@Transactional
public class ContractorDaoImpl extends SuperDaoImpl<Contractor> implements ContractorDao{

}
