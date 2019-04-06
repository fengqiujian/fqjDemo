package com.enjoyhis.persistence.mssql.dao;

import java.util.List;

import com.enjoyhis.persistence.mssql.po.Sfsfxm;

public interface SfsfxmMapper {

	// int deleteByPrimaryKey(Long sfxmId);

	// int insert(Sfsfxm record);

	// int insertSelective(Sfsfxm record);

	Sfsfxm selectByPrimaryKey(Long sfxmId);

	// int updateByPrimaryKeySelective(Sfsfxm record);

	// int updateByPrimaryKey(Sfsfxm record);

	Sfsfxm selectOne(Sfsfxm record);

	List<Sfsfxm> selectList(Sfsfxm record);

	int selectCount(Sfsfxm record);

	// int deleteSelective(Sfsfxm record);
}