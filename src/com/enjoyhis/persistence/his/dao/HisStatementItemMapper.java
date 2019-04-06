package com.enjoyhis.persistence.his.dao;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisStatementItem;

public interface HisStatementItemMapper {

	int deleteByPrimaryKey(Long id);

	int insert(HisStatementItem record);

	int insertSelective(HisStatementItem record);

	HisStatementItem selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(HisStatementItem record);

	int updateByPrimaryKey(HisStatementItem record);

	HisStatementItem selectOne(HisStatementItem record);

	List<HisStatementItem> selectList(HisStatementItem record);

	int selectCount(HisStatementItem record);

	int deleteSelective(HisStatementItem record);
}