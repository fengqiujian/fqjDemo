package com.enjoyhis.persistence.his.dao;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisServiceItemFl;

public interface HisServiceItemFlMapper {

	int deleteByPrimaryKey(String itemCode);

	int insert(HisServiceItemFl record);

	int insertSelective(HisServiceItemFl record);

	HisServiceItemFl selectByPrimaryKey(String itemCode);

	int updateByPrimaryKeySelective(HisServiceItemFl record);

	int updateByPrimaryKey(HisServiceItemFl record);

	HisServiceItemFl selectOne(HisServiceItemFl record);

	List<HisServiceItemFl> selectList(HisServiceItemFl record);

	int selectCount(HisServiceItemFl record);

	int deleteSelective(HisServiceItemFl record);
}