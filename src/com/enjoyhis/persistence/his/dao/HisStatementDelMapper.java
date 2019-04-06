package com.enjoyhis.persistence.his.dao;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisStatementDel;

public interface HisStatementDelMapper {

	int deleteByPrimaryKey(Long id);

	int insert(HisStatementDel record);

	int insertSelective(HisStatementDel record);

	HisStatementDel selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(HisStatementDel record);

	int updateByPrimaryKey(HisStatementDel record);

	HisStatementDel selectOne(HisStatementDel record);

	List<HisStatementDel> selectList(HisStatementDel record);

	int selectCount(HisStatementDel record);

	int deleteSelective(HisStatementDel record);
}