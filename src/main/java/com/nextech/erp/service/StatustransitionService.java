package com.nextech.erp.service;

import java.util.List;
import com.nextech.erp.model.Statustransition;

public interface StatustransitionService {
	public boolean addStatustransition(Statustransition statustransition)
			throws Exception;

	public Statustransition getStatustransitionById(long id) throws Exception;

	public List<Statustransition> getStatustransitionList() throws Exception;

	public boolean deleteStatustransition(long id) throws Exception;

	public Statustransition updateStatustransition(
			Statustransition statustransition) throws Exception;
}

