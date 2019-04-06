package com.enjoyhis.persistence.mssql.dao;

import java.util.List;

import com.enjoyhis.persistence.mssql.po.BrAdvances;

public interface BrAdvancesMapper {

	// int insert(BrAdvances record);
	//
	// int insertSelective(BrAdvances record);

	BrAdvances selectOne(BrAdvances record);

	List<BrAdvances> selectList(BrAdvances record);

	int selectCount(BrAdvances record);

	// int deleteSelective(BrAdvances record);
}