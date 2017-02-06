package com.nextech.erp.dao;

import java.util.List;
import com.nextech.erp.model.Statustransition;

public interface StatustransitionDao {
	public Long addStatustransition(Statustransition statustransition)
			throws Exception;

	public Statustransition getStatustransitionById(long id) throws Exception;

	public List<Statustransition> getStatustransitionList() throws Exception;

	public boolean deleteStatustransition(long id) throws Exception;

	public Statustransition updateStatustransition(
			Statustransition statustransition) throws Exception;
	
	public Statustransition getStatustransitionByEmail(String email) throws Exception;
}
