package com.enjoyhis.persistence.his.dao;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisDocroom;

public interface HisDocroomMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(HisDocroom record);

	int insertSelective(HisDocroom record);

	HisDocroom selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(HisDocroom record);

	int updateByPrimaryKey(HisDocroom record);

	HisDocroom selectOne(HisDocroom record);

	List<HisDocroom> selectList(HisDocroom record);

	int selectCount(HisDocroom record);

	int deleteSelective(HisDocroom record);
}