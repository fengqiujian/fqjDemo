package com.enjoyhis.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.dao.AdminRoleDao;
import com.enjoyhis.persistence.his.po.AdminRole;

@Service("adminRoleService")
public class AdminRoleService {
	@Autowired
	private AdminRoleDao adminRoleDao;

	public AdminRole findById(Integer id) {
		return adminRoleDao.selectByPrimaryKey(id);
	}
	public Map<Integer,String> getRoleName(){
		List<AdminRole> list = adminRoleDao.selectList(new AdminRole());
		Map<Integer,String> map = new HashMap<>();
		for(AdminRole ar : list){
			map.put(ar.getId(), ar.getRoleName());
		}
		return map;
	}

}
