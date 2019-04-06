package com.enjoyhis.persistence.his.dao;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisDict;

public interface HisDictMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(HisDict record);

	int insertSelective(HisDict record);

	HisDict selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(HisDict record);

	int updateByPrimaryKey(HisDict record);

	HisDict selectOne(HisDict record);

	List<HisDict> selectList(HisDict record);

	int selectCount(HisDict record);

	int deleteSelective(HisDict record);
}