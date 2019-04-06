package com.enjoyhis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.dao.AdminRoleMenuMappingDao;
import com.enjoyhis.persistence.his.po.AdminRoleMenuMapping;

@Service("adminRoleMenuService")
public class AdminRoleMenuService {
	@Autowired
	AdminRoleMenuMappingDao adminRoleMenuMappingMapper;

	public List<AdminRoleMenuMapping> getMenuId(AdminRoleMenuMapping hisEmployee) {
		List<AdminRoleMenuMapping> list = adminRoleMenuMappingMapper.selectList(hisEmployee);
		return list;
	}
}
