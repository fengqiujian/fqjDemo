package com.enjoyhis.persistence.his.dao;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisGroupEmployee;

public interface HisGroupemployeeMapper {

	int deleteByPrimaryKey(Long id);

	int insert(HisGroupEmployee record);

	int insertSelective(HisGroupEmployee record);

	HisGroupEmployee selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(HisGroupEmployee record);

	int updateByPrimaryKey(HisGroupEmployee record);

	HisGroupEmployee selectOne(HisGroupEmployee record);

	List<HisGroupEmployee> selectList(HisGroupEmployee record);

	int selectCount(HisGroupEmployee record);

	int deleteSelective(HisGroupEmployee record);
}