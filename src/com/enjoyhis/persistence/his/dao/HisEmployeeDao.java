package com.enjoyhis.persistence.his.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.po.HisEmployee;

@Service("hisEmployeeDao")
public class HisEmployeeDao {

	@Autowired
	HisEmployeeMapper hisEmployeeMapper;

	public int deleteByPrimaryKey(Long id) {
		return hisEmployeeMapper.deleteByPrimaryKey(id);
	}

	public int insert(HisEmployee record) {
		return hisEmployeeMapper.insert(record);
	}

	public int insertSelective(HisEmployee record) {
		return hisEmployeeMapper.insertSelective(record);
	}

	public HisEmployee selectByPrimaryKey(Long id) {
		return hisEmployeeMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(HisEmployee record) {
		return hisEmployeeMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(HisEmployee record) {
		return hisEmployeeMapper.updateByPrimaryKey(record);
	}

	public HisEmployee selectOne(HisEmployee record) {
		return hisEmployeeMapper.selectOne(record);
	}

	public List<HisEmployee> selectList(HisEmployee record) {
		return hisEmployeeMapper.selectList(record);
	}

	public int selectCount(HisEmployee record) {
		return hisEmployeeMapper.selectCount(record);
	}

	public int deleteSelective(HisEmployee record) {
		return hisEmployeeMapper.deleteSelective(record);
	}
}
