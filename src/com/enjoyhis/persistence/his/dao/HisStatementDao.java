package com.enjoyhis.persistence.his.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.po.HisStatement;

@Service("hisStatementDao")
public class HisStatementDao {

	@Autowired
	HisStatementMapper hisStatementMapper;

	public int deleteByPrimaryKey(Long id) {
		return hisStatementMapper.deleteByPrimaryKey(id);
	}

	public int insert(HisStatement record) {
		return hisStatementMapper.insert(record);
	}

	public int insertSelective(HisStatement record) {
		return hisStatementMapper.insertSelective(record);
	}

	public HisStatement selectByPrimaryKey(Long id) {
		return hisStatementMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(HisStatement record) {
		return hisStatementMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(HisStatement record) {
		return hisStatementMapper.updateByPrimaryKey(record);
	}

	public HisStatement selectOne(HisStatement record) {
		return hisStatementMapper.selectOne(record);
	}

	public List<HisStatement> selectList(HisStatement record) {
		return hisStatementMapper.selectList(record);
	}

	public int selectCount(HisStatement record) {
		return hisStatementMapper.selectCount(record);
	}

	public int deleteSelective(HisStatement record) {
		return hisStatementMapper.deleteSelective(record);
	}
	
	public List<Object> selectDateList(HisStatement record){
		return hisStatementMapper.selectDateList(record);
	}
}
