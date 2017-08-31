package com.enjoyhis.persistence.mssql.dao;

import java.util.List;

import com.enjoyhis.persistence.mssql.po.Sfsftjtype;

public interface SfsftjtypeMapper {

	// int deleteByPrimaryKey(String id);

	// int insert(Sfsftjtype record);

	// int insertSelective(Sfsftjtype record);

	Sfsftjtype selectByPrimaryKey(String id);

	// int updateByPrimaryKeySelective(Sfsftjtype record);

	// int updateByPrimaryKey(Sfsftjtype record);

	Sfsftjtype selectOne(Sfsftjtype record);

	List<Sfsftjtype> selectList(Sfsftjtype record);

	int selectCount(Sfsftjtype record);

	// int deleteSelective(Sfsftjtype record);
}