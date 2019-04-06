package com.enjoyhis.persistence.his.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.po.AdminMenu;

@Service("adminMenuDao")
public class AdminMenuDao {

	@Autowired
	AdminMenuMapper adminMenuMapper;

	public int deleteByPrimaryKey(Integer id) {
		return adminMenuMapper.deleteByPrimaryKey(id);
	}

	public int insert(AdminMenu record) {
		return adminMenuMapper.insert(record);
	}

	public int insertSelective(AdminMenu record) {
		return adminMenuMapper.insertSelective(record);
	}

	public AdminMenu selectByPrimaryKey(Integer id) {
		return adminMenuMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(AdminMenu record) {
		return adminMenuMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(AdminMenu record) {
		return adminMenuMapper.updateByPrimaryKey(record);
	}

	public AdminMenu selectOne(AdminMenu record) {
		return adminMenuMapper.selectOne(record);
	}

	public List<AdminMenu> selectList(AdminMenu record) {
		return adminMenuMapper.selectList(record);
	}

	public int selectCount(AdminMenu record) {
		return adminMenuMapper.selectCount(record);
	}

	public int deleteSelective(AdminMenu record) {
		return adminMenuMapper.deleteSelective(record);
	}
}
