package com.enjoyhis.persistence.his.dao;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisStation;

public interface HisStationMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(HisStation record);

	int insertSelective(HisStation record);

	HisStation selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(HisStation record);

	int updateByPrimaryKey(HisStation record);

	HisStation selectOne(HisStation record);

	List<HisStation> selectList(HisStation record);

	int selectCount(HisStation record);

	int deleteSelective(HisStation record);
}