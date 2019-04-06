package com.enjoyhis.persistence.his.dao;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisServiceItemXm;

public interface HisServiceItemXmMapper {

	int deleteByPrimaryKey(String itemCode);

	int insert(HisServiceItemXm record);

	int insertSelective(HisServiceItemXm record);

	HisServiceItemXm selectByPrimaryKey(String itemCode);

	int updateByPrimaryKeySelective(HisServiceItemXm record);

	int updateByPrimaryKey(HisServiceItemXm record);

	HisServiceItemXm selectOne(HisServiceItemXm record);

	List<HisServiceItemXm> selectList(HisServiceItemXm record);

	int selectCount(HisServiceItemXm record);

	int deleteSelective(HisServiceItemXm record);
	
	int insertAll (List<HisServiceItemXm> list);

	int updateAll (List<HisServiceItemXm> list);
}