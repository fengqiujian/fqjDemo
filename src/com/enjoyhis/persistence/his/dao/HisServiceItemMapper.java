package com.enjoyhis.persistence.his.dao;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisServiceItem;

public interface HisServiceItemMapper {

	int deleteByPrimaryKey(String itemCode);

	int insert(HisServiceItem record);

	int insertSelective(HisServiceItem record);

	HisServiceItem selectByPrimaryKey(String itemCode);

	int updateByPrimaryKeySelective(HisServiceItem record);

	int updateByPrimaryKey(HisServiceItem record);

	HisServiceItem selectOne(HisServiceItem record);

	List<HisServiceItem> selectList(HisServiceItem record);

	int selectCount(HisServiceItem record);

	int deleteSelective(HisServiceItem record);
}