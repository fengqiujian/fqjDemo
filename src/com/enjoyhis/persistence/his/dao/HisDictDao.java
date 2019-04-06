package com.enjoyhis.persistence.his.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.po.HisDict;

@Service("hisDictDao")
public class HisDictDao {

	@Autowired
	HisDictMapper hisDictMapper;

	public int deleteByPrimaryKey(Integer id) {
		return hisDictMapper.deleteByPrimaryKey(id);
	}

	public int insert(HisDict record) {
		return hisDictMapper.insert(record);
	}

	public int insertSelective(HisDict record) {
		return hisDictMapper.insertSelective(record);
	}

	public HisDict selectByPrimaryKey(Integer id) {
		return hisDictMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(HisDict record) {
		return hisDictMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(HisDict record) {
		return hisDictMapper.updateByPrimaryKey(record);
	}

	public HisDict selectOne(HisDict record) {
		return hisDictMapper.selectOne(record);
	}

	public List<HisDict> selectList(HisDict record) {
		return hisDictMapper.selectList(record);
	}

	public int selectCount(HisDict record) {
		return hisDictMapper.selectCount(record);
	}

	public int deleteSelective(HisDict record) {
		return hisDictMapper.deleteSelective(record);
	}
}
