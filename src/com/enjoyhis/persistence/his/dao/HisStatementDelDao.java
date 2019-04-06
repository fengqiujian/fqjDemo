package com.enjoyhis.persistence.his.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.po.HisStatementDel;

@Service("hisStatementDelDao")
public class HisStatementDelDao {

	@Autowired
	HisStatementDelMapper hisStatementDelMapper;

	public int deleteByPrimaryKey(Long id) {
		return hisStatementDelMapper.deleteByPrimaryKey(id);
	}

	public int insert(HisStatementDel record) {
		return hisStatementDelMapper.insert(record);
	}

	public int insertSelective(HisStatementDel record) {
		return hisStatementDelMapper.insertSelective(record);
	}

	public HisStatementDel selectByPrimaryKey(Long id) {
		return hisStatementDelMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(HisStatementDel record) {
		return hisStatementDelMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(HisStatementDel record) {
		return hisStatementDelMapper.updateByPrimaryKey(record);
	}

	public HisStatementDel selectOne(HisStatementDel record) {
		return hisStatementDelMapper.selectOne(record);
	}

	public List<HisStatementDel> selectList(HisStatementDel record) {
		return hisStatementDelMapper.selectList(record);
	}

	public int selectCount(HisStatementDel record) {
		return hisStatementDelMapper.selectCount(record);
	}

	public int deleteSelective(HisStatementDel record) {
		return hisStatementDelMapper.deleteSelective(record);
	}
}
