package com.nextech.erp.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.nextech.erp.dao.StatusDao;
import com.nextech.erp.model.Status;
import com.nextech.erp.service.StatusService;

public class StatusServiceImpl implements StatusService {
	
	@Autowired
	StatusDao statusDao;

	@Override
	public boolean addStatus(Status status) throws Exception {
		status.setCreatedDate(new Timestamp(new Date().getTime()));
		return statusDao.addStatus(status);
	}

	@Override
	public Status getStatusById(long id) throws Exception {
		return statusDao.getStatusById(id);
	}

	@Override
	public List<Status> getStatusist() throws Exception {
		return statusDao.getStatusist();
	}

	@Override
	public boolean deleteStatus(long id) throws Exception {
		return statusDao.deleteStatus(id);
	}

	@Override
	public Status updateStatus(Status status) throws Exception {
		status.setUpdatedDate(new Timestamp(new Date().getTime()));
		return statusDao.updateStatus(status);
	}
}
