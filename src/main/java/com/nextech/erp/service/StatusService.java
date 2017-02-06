package com.nextech.erp.service;

import java.util.List;

import com.nextech.erp.model.Status;

public interface StatusService {
	public Long addStatus(Status status) throws Exception;

	public Status getStatusById(long id) throws Exception;

	public List<Status> getStatusist() throws Exception;

	public boolean deleteStatus(long id) throws Exception;

	public Status updateStatus(Status status) throws Exception;
}


