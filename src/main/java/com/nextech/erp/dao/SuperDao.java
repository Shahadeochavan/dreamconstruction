package com.nextech.erp.dao;

import java.util.List;

public interface SuperDao<T> {
	public Long add(T bean) throws Exception;

	public T getById(Class<?> z,long id) throws Exception;

	public List<T> getList(Class<?> z) throws Exception;

	public boolean delete(Class<?> z,long id) throws Exception;

	public T update(T bean) throws Exception;
}
