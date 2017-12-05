package com.nextech.dreamConstruction.daoImpl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nextech.dreamConstruction.dao.StatusDao;
import com.nextech.dreamConstruction.model.Status;

@Repository
@Transactional
public class StatusDaoImpl extends SuperDaoImpl<Status> implements StatusDao{

}