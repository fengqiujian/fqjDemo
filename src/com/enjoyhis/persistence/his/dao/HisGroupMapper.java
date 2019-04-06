package com.enjoyhis.persistence.his.dao;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisGroup;

public interface HisGroupMapper {

	int deleteByPrimaryKey(Long id);

	int insert(HisGroup record);

	int insertSelective(HisGroup record);

	HisGroup selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(HisGroup record);

	int updateByPrimaryKey(HisGroup record);

	HisGroup selectOne(HisGroup record);

	List<HisGroup> selectList(HisGroup record);

	int selectCount(HisGroup record);

	int deleteSelective(HisGroup record);
}