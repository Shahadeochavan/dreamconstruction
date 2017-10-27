package com.nextech.systeminventory.daoImpl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nextech.systeminventory.dao.StatusDao;
import com.nextech.systeminventory.model.Status;

@Repository
@Transactional
public class StatusDaoImpl extends SuperDaoImpl<Status> implements StatusDao{

}