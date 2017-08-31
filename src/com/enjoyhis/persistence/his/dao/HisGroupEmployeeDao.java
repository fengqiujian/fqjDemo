package com.enjoyhis.persistence.his.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.po.HisGroupEmployee;

//import com.enjoyhis.persistence.his.po.HisGroup;

@Service("hisGroupEmployeeDao")
public class HisGroupEmployeeDao {

	@Autowired
	HisGroupemployeeMapper HisGroupemployeeMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return HisGroupemployeeMapper.deleteByPrimaryKey(id);
	}

	public int insert(HisGroupEmployee record) {
		return HisGroupemployeeMapper.insert(record);
	}

	public int insertSelective(HisGroupEmployee record) {
		return HisGroupemployeeMapper.insertSelective(record);
	}

	public HisGroupEmployee selectByPrimaryKey(Long id) {
		return HisGroupemployeeMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(HisGroupEmployee record) {
		return HisGroupemployeeMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(HisGroupEmployee record) {
		return HisGroupemployeeMapper.updateByPrimaryKey(record);
	}

	public HisGroupEmployee selectOne(HisGroupEmployee record) {
		return HisGroupemployeeMapper.selectOne(record);
	}

	public List<HisGroupEmployee> selectList(HisGroupEmployee record) {
		return HisGroupemployeeMapper.selectList(record);
	}

	public int selectCount(HisGroupEmployee record) {
		return HisGroupemployeeMapper.selectCount(record);
	}

	public int deleteSelective(HisGroupEmployee record) {
		return HisGroupemployeeMapper.deleteSelective(record);
	}
}
