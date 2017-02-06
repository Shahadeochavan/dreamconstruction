package com.nextech.erp.service;

import java.util.List;

import com.nextech.erp.model.Rawmaterial;

public interface RawmaterialService {
	public Long addRawmaterial(Rawmaterial rawmaterial) throws Exception;

	public Rawmaterial getRawmaterialById(long id) throws Exception;

	public List<Rawmaterial> getRawmaterialList() throws Exception;

	public boolean deleteRawmaterial(long id) throws Exception;

	public Rawmaterial updateRawmaterial(Rawmaterial rawmaterial)
			throws Exception;
}