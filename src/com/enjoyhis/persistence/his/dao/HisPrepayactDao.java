package com.enjoyhis.persistence.his.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.po.HisPrepayact;

@Service("hisPrepayactDao")
public class HisPrepayactDao {

	@Autowired
	HisPrepayactMapper hisPrepayactMapper;

	public int deleteByPrimaryKey(Integer id) {
		return hisPrepayactMapper.deleteByPrimaryKey(id);
	}

	public int insert(HisPrepayact record) {
		return hisPrepayactMapper.insert(record);
	}

	public int insertSelective(HisPrepayact record) {
		return hisPrepayactMapper.insertSelective(record);
	}

	public HisPrepayact selectByPrimaryKey(Integer id) {
		return hisPrepayactMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(HisPrepayact record) {
		return hisPrepayactMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(HisPrepayact record) {
		return hisPrepayactMapper.updateByPrimaryKey(record);
	}

	public HisPrepayact selectOne(HisPrepayact record) {
		return hisPrepayactMapper.selectOne(record);
	}

	public List<HisPrepayact> selectList(HisPrepayact record) {
		return hisPrepayactMapper.selectList(record);
	}

	public int selectCount(HisPrepayact record) {
		return hisPrepayactMapper.selectCount(record);
	}

	public int deleteSelective(HisPrepayact record) {
		return hisPrepayactMapper.deleteSelective(record);
	}
}
