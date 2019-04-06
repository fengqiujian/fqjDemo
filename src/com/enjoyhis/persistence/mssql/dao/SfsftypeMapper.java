package com.enjoyhis.persistence.mssql.dao;

import java.util.List;

import com.enjoyhis.persistence.mssql.po.Sfsftype;

public interface SfsftypeMapper {

	// int deleteByPrimaryKey(Integer id);

	// int insert(Sfsftype record);

	// int insertSelective(Sfsftype record);

	Sfsftype selectByPrimaryKey(Integer id);

	// int updateByPrimaryKeySelective(Sfsftype record);

	// int updateByPrimaryKey(Sfsftype record);

	Sfsftype selectOne(Sfsftype record);

	List<Sfsftype> selectList(Sfsftype record);

	int selectCount(Sfsftype record);

	// int deleteSelective(Sfsftype record);
}