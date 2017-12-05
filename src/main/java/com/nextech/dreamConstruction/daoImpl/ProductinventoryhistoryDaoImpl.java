package com.nextech.dreamConstruction.daoImpl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nextech.dreamConstruction.dao.ProductinventoryhistoryDao;
import com.nextech.dreamConstruction.model.Productinventoryhistory;

@Repository
@Transactional
public class ProductinventoryhistoryDaoImpl extends SuperDaoImpl<Productinventoryhistory>implements ProductinventoryhistoryDao {

}
