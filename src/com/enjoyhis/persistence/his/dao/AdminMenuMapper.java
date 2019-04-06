package com.enjoyhis.persistence.his.dao;

import java.util.List;

import com.enjoyhis.persistence.his.po.AdminMenu;

public interface AdminMenuMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(AdminMenu record);

	int insertSelective(AdminMenu record);

	AdminMenu selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(AdminMenu record);

	int updateByPrimaryKey(AdminMenu record);

	AdminMenu selectOne(AdminMenu record);

	List<AdminMenu> selectList(AdminMenu record);

	int selectCount(AdminMenu record);

	int deleteSelective(AdminMenu record);
}