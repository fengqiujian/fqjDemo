package com.enjoyhis.persistence.his.dao;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisEmployee;

public interface HisEmployeeMapper {

	int deleteByPrimaryKey(Long id);

	int insert(HisEmployee record);

	int insertSelective(HisEmployee record);

	HisEmployee selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(HisEmployee record);

	int updateByPrimaryKey(HisEmployee record);

	HisEmployee selectOne(HisEmployee record);

	List<HisEmployee> selectList(HisEmployee record);

	int selectCount(HisEmployee record);

	int deleteSelective(HisEmployee record);
}