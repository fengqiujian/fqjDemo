package com.enjoyhis.persistence.his.dao;

import java.util.List;

import com.enjoyhis.persistence.his.po.AdminRole;

public interface AdminRoleMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(AdminRole record);

	int insertSelective(AdminRole record);

	AdminRole selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(AdminRole record);

	int updateByPrimaryKey(AdminRole record);

	AdminRole selectOne(AdminRole record);

	List<AdminRole> selectList(AdminRole record);

	int selectCount(AdminRole record);

	int deleteSelective(AdminRole record);
}