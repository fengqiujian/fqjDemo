package com.enjoyhis.persistence.mssql.dao;

import java.util.List;

import com.enjoyhis.persistence.mssql.po.Brhz;

public interface BrhzMapper {

	// int deleteByPrimaryKey(Long patId);
	//
	// int insert(Brhz record);
	//
	// int insertSelective(Brhz record);

	Brhz selectByPrimaryKey(Long patId);

	// int updateByPrimaryKeySelective(Brhz record);
	//
	// int updateByPrimaryKey(Brhz record);

	Brhz selectOne(Brhz record);

	List<Brhz> selectList(Brhz record);

	int selectCount(Brhz record);

	// int deleteSelective(Brhz record);
}