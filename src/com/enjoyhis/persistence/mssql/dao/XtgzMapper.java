package com.enjoyhis.persistence.mssql.dao;

import java.util.List;

import com.enjoyhis.persistence.mssql.po.Xtgz;

public interface XtgzMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(Xtgz record);

	int insertSelective(Xtgz record);

	Xtgz selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Xtgz record);

	int updateByPrimaryKey(Xtgz record);

	Xtgz selectOne(Xtgz record);

	List<Xtgz> selectList(Xtgz record);

	int selectCount(Xtgz record);

	int deleteSelective(Xtgz record);
}