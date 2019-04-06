package com.enjoyhis.persistence.his.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.po.HisGroup;

@Service("HisGroupDao")
public class HisGroupDao {

	@Autowired
	HisGroupMapper hisGroupMapper;

	public int deleteByPrimaryKey(Long id) {
		return hisGroupMapper.deleteByPrimaryKey(id);
	}

	public int insert(HisGroup record) {
		return hisGroupMapper.insert(record);
	}

	public int insertSelective(HisGroup record) {
		return hisGroupMapper.insertSelective(record);
	}

	public HisGroup selectByPrimaryKey(Long id) {
		return hisGroupMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(HisGroup record) {
		return hisGroupMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(HisGroup record) {
		return hisGroupMapper.updateByPrimaryKey(record);
	}

	public HisGroup selectOne(HisGroup record) {
		return hisGroupMapper.selectOne(record);
	}

	public List<HisGroup> selectList(HisGroup record) {
		return hisGroupMapper.selectList(record);
	}

	public int selectCount(HisGroup record) {
		return hisGroupMapper.selectCount(record);
	}

	public int deleteSelective(HisGroup record) {
		return hisGroupMapper.deleteSelective(record);
	}
}
