package com.enjoyhis.persistence.mssql.dao;

import java.util.List;

import com.enjoyhis.persistence.mssql.po.Hruser;

public interface HruserMapper {

	// int deleteByPrimaryKey(Long userId);

	// int insert(Hruser record);

	// int insertSelective(Hruser record);

	Hruser selectByPrimaryKey(Long userId);

	// int updateByPrimaryKeySelective(Hruser record);

	// int updateByPrimaryKey(Hruser record);

	Hruser selectOne(Hruser record);

	List<Hruser> selectList(Hruser record);

	int selectCount(Hruser record);

	// int deleteSelective(Hruser record);
}