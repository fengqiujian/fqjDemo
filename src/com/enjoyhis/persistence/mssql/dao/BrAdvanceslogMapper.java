package com.enjoyhis.persistence.mssql.dao;

import java.util.List;

import com.enjoyhis.persistence.mssql.po.BrAdvanceslog;

public interface BrAdvanceslogMapper {

	// int deleteByPrimaryKey(String rowguid);
	//
	// int insert(BrAdvanceslog record);
	//
	// int insertSelective(BrAdvanceslog record);

	BrAdvanceslog selectByPrimaryKey(String rowguid);

	// int updateByPrimaryKeySelective(BrAdvanceslog record);
	//
	// int updateByPrimaryKey(BrAdvanceslog record);

	BrAdvanceslog selectOne(BrAdvanceslog record);

	List<BrAdvanceslog> selectList(BrAdvanceslog record);

	int selectCount(BrAdvanceslog record);

	// int deleteSelective(BrAdvanceslog record);
}