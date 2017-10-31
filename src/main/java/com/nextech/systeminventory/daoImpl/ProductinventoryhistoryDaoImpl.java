package com.nextech.systeminventory.daoImpl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nextech.systeminventory.dao.ProductinventoryhistoryDao;
import com.nextech.systeminventory.model.Productinventoryhistory;

@Repository
@Transactional
public class ProductinventoryhistoryDaoImpl extends SuperDaoImpl<Productinventoryhistory>implements ProductinventoryhistoryDao {

}
