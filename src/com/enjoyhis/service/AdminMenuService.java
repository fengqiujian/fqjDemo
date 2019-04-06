package com.enjoyhis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.dao.AdminMenuDao;
import com.enjoyhis.persistence.his.po.AdminMenu;

@Service("adminMenuService")
public class AdminMenuService {
	@Autowired
	AdminMenuDao adminMenuMapper;
	
	public List<AdminMenu> getAdminMenuList(AdminMenu record){
		record.setStatus(0);
		List<AdminMenu> list = adminMenuMapper.selectList(record);
		return list;
	}
}
