package com.enjoyhis.persistence.his.dao;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisStatement;

public interface HisStatementMapper {

	int deleteByPrimaryKey(Long id);

	int insert(HisStatement record);

	int insertSelective(HisStatement record);

	HisStatement selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(HisStatement record);

	int updateByPrimaryKey(HisStatement record);

	HisStatement selectOne(HisStatement record);

	List<HisStatement> selectList(HisStatement record);

	int selectCount(HisStatement record);

	int deleteSelective(HisStatement record);
	
	List<Object> selectDateList(HisStatement record);
}