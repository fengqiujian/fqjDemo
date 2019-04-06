package com.enjoyhis.persistence.his.dao;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisXtXray;

public interface HisXtXrayMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(HisXtXray record);

	int insertSelective(HisXtXray record);

	HisXtXray selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(HisXtXray record);

	int updateByPrimaryKey(HisXtXray record);

	HisXtXray selectOne(HisXtXray record);

	List<HisXtXray> selectList(HisXtXray record);

	int selectCount(HisXtXray record);

	int deleteSelective(HisXtXray record);
}