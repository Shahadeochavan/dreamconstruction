package com.nextech.systeminventory.daoImpl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nextech.systeminventory.dao.PurchaseDao;
import com.nextech.systeminventory.model.Purchase;

@Repository
@Transactional
public class PurchaseDaoImpl extends SuperDaoImpl<Purchase> implements PurchaseDao {

}
