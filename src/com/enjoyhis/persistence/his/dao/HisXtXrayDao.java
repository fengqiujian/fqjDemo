package com.enjoyhis.persistence.his.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.po.HisXtXray;

@Service("hisXtXrayDao")
public class HisXtXrayDao {

	@Autowired
	HisXtXrayMapper hisXtXrayMapper;

	public int deleteByPrimaryKey(Integer id) {
		return hisXtXrayMapper.deleteByPrimaryKey(id);
	}

	public int insert(HisXtXray record) {
		return hisXtXrayMapper.insert(record);
	}

	public int insertSelective(HisXtXray record) {
		return hisXtXrayMapper.insertSelective(record);
	}

	public HisXtXray selectByPrimaryKey(Integer id) {
		return hisXtXrayMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(HisXtXray record) {
		return hisXtXrayMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(HisXtXray record) {
		return hisXtXrayMapper.updateByPrimaryKey(record);
	}

	public HisXtXray selectOne(HisXtXray record) {
		return hisXtXrayMapper.selectOne(record);
	}

	public List<HisXtXray> selectList(HisXtXray record) {
		return hisXtXrayMapper.selectList(record);
	}

	public int selectCount(HisXtXray record) {
		return hisXtXrayMapper.selectCount(record);
	}

	public int deleteSelective(HisXtXray record) {
		return hisXtXrayMapper.deleteSelective(record);
	}
}
