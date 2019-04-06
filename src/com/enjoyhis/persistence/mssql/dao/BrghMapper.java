package com.enjoyhis.persistence.mssql.dao;

import java.util.List;

import com.enjoyhis.persistence.mssql.po.Brgh;

public interface BrghMapper {

	// int deleteByPrimaryKey(Long ghId);
	//
	// int insert(Brgh record);
	//
	// int insertSelective(Brgh record);

	Brgh selectByPrimaryKey(Long ghId);

	// int updateByPrimaryKeySelective(Brgh record);
	//
	// int updateByPrimaryKey(Brgh record);

	Brgh selectOne(Brgh record);

	List<Brgh> selectList(Brgh record);

	int selectCount(Brgh record);

	// int deleteSelective(Brgh record);
}