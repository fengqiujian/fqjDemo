package com.enjoyhis.persistence.his.dao;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisStatementItemDel;

public interface HisStatementItemDelMapper {

	int deleteByPrimaryKey(Long id);

	int insert(HisStatementItemDel record);

	int insertSelective(HisStatementItemDel record);

	HisStatementItemDel selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(HisStatementItemDel record);

	int updateByPrimaryKey(HisStatementItemDel record);

	HisStatementItemDel selectOne(HisStatementItemDel record);

	List<HisStatementItemDel> selectList(HisStatementItemDel record);

	int selectCount(HisStatementItemDel record);

	int deleteSelective(HisStatementItemDel record);
}