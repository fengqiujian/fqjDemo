package com.enjoyhis.persistence.his.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.po.SysConfig;

@Service("sysConfigDao")
public class SysConfigDao {

	@Autowired
	SysConfigMapper sysConfigMapper;

	public int deleteByPrimaryKey(Integer id) {
		return sysConfigMapper.deleteByPrimaryKey(id);
	}

	public int insert(SysConfig record) {
		return sysConfigMapper.insert(record);
	}

	public int insertSelective(SysConfig record) {
		return sysConfigMapper.insertSelective(record);
	}

	public SysConfig selectByPrimaryKey(Integer id) {
		return sysConfigMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(SysConfig record) {
		return sysConfigMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(SysConfig record) {
		return sysConfigMapper.updateByPrimaryKey(record);
	}

	public SysConfig selectOne(SysConfig record) {
		return sysConfigMapper.selectOne(record);
	}

	public List<SysConfig> selectList(SysConfig record) {
		return sysConfigMapper.selectList(record);
	}

	public int selectCount(SysConfig record) {
		return sysConfigMapper.selectCount(record);
	}

	public int deleteSelective(SysConfig record) {
		return sysConfigMapper.deleteSelective(record);
	}
}
