package com.enjoyhis.persistence.his.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.po.HisDepart;

@Service("hisDepartDao")
public class HisDepartDao {

	@Autowired
	HisDepartMapper hisDepartMapper;

	public int deleteByPrimaryKey(Integer id) {
		return hisDepartMapper.deleteByPrimaryKey(id);
	}

	public int insert(HisDepart record) {
		return hisDepartMapper.insert(record);
	}

	public int insertSelective(HisDepart record) {
		return hisDepartMapper.insertSelective(record);
	}

	public HisDepart selectByPrimaryKey(Integer id) {
		return hisDepartMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(HisDepart record) {
		return hisDepartMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(HisDepart record) {
		return hisDepartMapper.updateByPrimaryKey(record);
	}

	public HisDepart selectOne(HisDepart record) {
		return hisDepartMapper.selectOne(record);
	}

	public List<HisDepart> selectList(HisDepart record) {
		return hisDepartMapper.selectList(record);
	}

	public int selectCount(HisDepart record) {
		return hisDepartMapper.selectCount(record);
	}

	public int deleteSelective(HisDepart record) {
		return hisDepartMapper.deleteSelective(record);
	}
}
