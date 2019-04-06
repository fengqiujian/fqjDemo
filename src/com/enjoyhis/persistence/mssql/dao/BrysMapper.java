package com.enjoyhis.persistence.mssql.dao;

import java.util.List;

import com.enjoyhis.persistence.mssql.po.Brys;

public interface BrysMapper {

	// int deleteByPrimaryKey(Long ysId);
	//
	// int insert(Brys record);
	//
	// int insertSelective(Brys record);

	Brys selectByPrimaryKey(Long ysId);

	// int updateByPrimaryKeySelective(Brys record);

	// int updateByPrimaryKey(Brys record);

	Brys selectOne(Brys record);

	List<Brys> selectList(Brys record);

	int selectCount(Brys record);

	// int deleteSelective(Brys record);
}