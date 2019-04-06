package com.enjoyhis.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.enjoyhis.persistence.his.dao.KeyValueMapDao;
import com.enjoyhis.util.JSONUtils;

@Service("keyValueMapService")
public class KeyValueMapService<T> {
	@Autowired
	private KeyValueMapDao<T> mapper;

	// 带条件、排序、指定列名的自由查询
	public List<T> query(String table, String keyColumn, String column, String where, String order, int isAscendent) {
		if (keyColumn == null) {
			keyColumn = "id";
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("table", table);
		param.put("keyColumn", keyColumn);
		param.put("column", column);
		param.put("where", where);

		param.put("order", order);
		return mapper.query(param);
	}

	public List<T> query4DropDown(String table, String column1, String column2, String sqlstr) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("table", table);
		param.put("column1", column1);
		param.put("column2", column2);
		// String sqlcolumn = "";
		// String sqlvalue = "";
		String wheresql = "";
		if (StringUtils.isNotBlank(sqlstr)) {
			JSONObject jsonObject = JSONUtils.toJSONObject(sqlstr);
			// sqlcolumn = (String) jsonObject.get("sqlcolumn");
			// sqlvalue = (String) jsonObject.get("sqlvalue");
			wheresql = (String) jsonObject.get("where");
		}
		// param.put("sqlcolumn", sqlcolumn);
		// param.put("sqlvalue", "'"+sqlvalue+"'");
		param.put("wheresql", wheresql);
		List<T> query = mapper.query(param);
		return query;
	}

	public List<T> query4DropDown2(String... columns) {
		Map<String, Object> param = new HashMap<String, Object>();
		String table = "";
		String column1 = "";
		String column2 = "";
		String wheresql = "";
		for (int i = 0; i < columns.length; i++) {
			if (i == 0)
				table = columns[i];
			if (i == 1)
				column1 = columns[i];
			if (i == 2)
				column2 = columns[i];
			if (i == 3) {
				JSONObject jsonObject = JSONUtils.toJSONObject(columns[i]);
				String sqlcolumn = (String) jsonObject.get("where");
				System.out.println("where = " + sqlcolumn);
			}
		}
		param.put("table", table);
		param.put("column1", column1);
		param.put("column2", column2);
		param.put("wheresql", wheresql);

		List<T> query = mapper.query(param);
		return query;
	}

	/**
	 * 
	 * @param table
	 * @param column1
	 * @param column2
	 * @param sqlstr
	 * @return
	 */
	public List<T> queryInfo(String table, String column1, String column2, String sqlstr) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("table", table);
		param.put("column1", column1);
		param.put("column2", column2);
		String wheresql = "";
		if (StringUtils.isNotBlank(sqlstr)) {
			JSONObject jsonObject = JSONUtils.toJSONObject(sqlstr);
			wheresql = (String) jsonObject.get("where");
		}
		param.put("wheresql", wheresql);
		List<T> query = mapper.querys(param);
		return query;
	}
}
