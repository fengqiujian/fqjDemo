package com.enjoyhis.persistence.his.dao;

import java.util.List;

import com.enjoyhis.persistence.his.po.SysConfig;

public interface SysConfigMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(SysConfig record);

	int insertSelective(SysConfig record);

	SysConfig selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SysConfig record);

	int updateByPrimaryKey(SysConfig record);

	SysConfig selectOne(SysConfig record);

	List<SysConfig> selectList(SysConfig record);

	int selectCount(SysConfig record);

	int deleteSelective(SysConfig record);
}