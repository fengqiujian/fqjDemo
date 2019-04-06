package com.enjoyhis.persistence.his.dao;

import java.util.List;

import com.enjoyhis.persistence.base.BaseDao;

/**
 * ButtonOfMenu Mapper
 * @author Administrator
 *
 */
public interface KeyValueMapDao<T> extends BaseDao<T> {
	public List<T> queryByTable(java.util.Map<String, Object> param);
	public List<T> queryByTableAndColumn(java.util.Map<String, Object> param);
	public List<T> queryByTableWithOrder(java.util.Map<String, Object> param);
	public List<T> queryByTableAndColumnWithOrder(java.util.Map<String, Object> param);
	//带条件、排序、指定列名的自由查询
	public List<T> query(java.util.Map<String, Object> param);
	public List<T> querys(java.util.Map<String, Object> param);
}
