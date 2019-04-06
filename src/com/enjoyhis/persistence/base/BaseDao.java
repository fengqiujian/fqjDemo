package com.enjoyhis.persistence.base;

public interface BaseDao<T> {

	public void add(T t);

	public void update(T t);

	public void updateBySelective(T t);

	public void delete(Object id);

	public T queryById(Object id);

}
