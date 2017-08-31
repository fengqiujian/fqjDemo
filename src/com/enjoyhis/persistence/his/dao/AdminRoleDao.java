package com.enjoyhis.persistence.his.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.po.AdminRole;

@Service("adminRoleDao")
public class AdminRoleDao {

	@Autowired
	AdminRoleMapper adminRoleMapper;

	public int deleteByPrimaryKey(Integer id) {
		return adminRoleMapper.deleteByPrimaryKey(id);
	}

	public int insert(AdminRole record) {
		return adminRoleMapper.insert(record);
	}

	public int insertSelective(AdminRole record) {
		return adminRoleMapper.insertSelective(record);
	}

	public AdminRole selectByPrimaryKey(Integer id) {
		return adminRoleMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(AdminRole record) {
		return adminRoleMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(AdminRole record) {
		return adminRoleMapper.updateByPrimaryKey(record);
	}

	public AdminRole selectOne(AdminRole record) {
		return adminRoleMapper.selectOne(record);
	}

	public List<AdminRole> selectList(AdminRole record) {
		return adminRoleMapper.selectList(record);
	}

	public int selectCount(AdminRole record) {
		return adminRoleMapper.selectCount(record);
	}

	public int deleteSelective(AdminRole record) {
		return adminRoleMapper.deleteSelective(record);
	}
}
