package com.enjoyhis.persistence.mssql.dao;

import java.util.List;

import com.enjoyhis.persistence.mssql.po.Brss;

public interface BrssMapper {

	// int deleteByPrimaryKey(Long ssId);
	//
	// int insert(Brss record);
	//
	// int insertSelective(Brss record);

	Brss selectByPrimaryKey(Long ssId);

	// int updateByPrimaryKeySelective(Brss record);
	//
	// int updateByPrimaryKey(Brss record);

	Brss selectOne(Brss record);

	List<Brss> selectList(Brss record);

	int selectCount(Brss record);

	// int deleteSelective(Brss record);
}