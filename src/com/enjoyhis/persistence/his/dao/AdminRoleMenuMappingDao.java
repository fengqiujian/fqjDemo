package com.enjoyhis.persistence.his.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.po.AdminRoleMenuMapping;

@Service("adminRoleMenuMappingDao")
public class AdminRoleMenuMappingDao {

	@Autowired
	AdminRoleMenuMappingMapper adminRoleMenuMappingMapper;

	public int deleteByPrimaryKey(Integer id) {
		return adminRoleMenuMappingMapper.deleteByPrimaryKey(id);
	}

	public int insert(AdminRoleMenuMapping record) {
		return adminRoleMenuMappingMapper.insert(record);
	}

	public int insertSelective(AdminRoleMenuMapping record) {
		return adminRoleMenuMappingMapper.insertSelective(record);
	}

	public AdminRoleMenuMapping selectByPrimaryKey(Integer id) {
		return adminRoleMenuMappingMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(AdminRoleMenuMapping record) {
		return adminRoleMenuMappingMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(AdminRoleMenuMapping record) {
		return adminRoleMenuMappingMapper.updateByPrimaryKey(record);
	}

	public AdminRoleMenuMapping selectOne(AdminRoleMenuMapping record) {
		return adminRoleMenuMappingMapper.selectOne(record);
	}

	public List<AdminRoleMenuMapping> selectList(AdminRoleMenuMapping record) {
		return adminRoleMenuMappingMapper.selectList(record);
	}

	public int selectCount(AdminRoleMenuMapping record) {
		return adminRoleMenuMappingMapper.selectCount(record);
	}

	public int deleteSelective(AdminRoleMenuMapping record) {
		return adminRoleMenuMappingMapper.deleteSelective(record);
	}
}
