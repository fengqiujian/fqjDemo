package com.enjoyhis.persistence.mssql.dao;

import java.util.List;

import com.enjoyhis.persistence.mssql.po.Xthosp;

public interface XthospMapper {

	// int deleteByPrimaryKey(Long id);
	//
	// int insert(Xthosp record);
	//
	// int insertSelective(Xthosp record);

	Xthosp selectByPrimaryKey(Long id);

	// int updateByPrimaryKeySelective(Xthosp record);
	//
	// int updateByPrimaryKeyWithBLOBs(Xthosp record);
	//
	// int updateByPrimaryKey(Xthosp record);

	Xthosp selectOne(Xthosp record);

	List<Xthosp> selectList(Xthosp record);

	int selectCount(Xthosp record);

	// int deleteSelective(Xthosp record);
}