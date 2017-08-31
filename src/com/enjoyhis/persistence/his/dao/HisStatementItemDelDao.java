package com.enjoyhis.persistence.his.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.po.HisStatementItemDel;

@Service("hisStatementItemDelDao")
public class HisStatementItemDelDao {

	@Autowired
	HisStatementItemDelMapper hisStatementItemDelMapper;

	public int deleteByPrimaryKey(Long id) {
		return hisStatementItemDelMapper.deleteByPrimaryKey(id);
	}

	public int insert(HisStatementItemDel record) {
		return hisStatementItemDelMapper.insert(record);
	}

	public int insertSelective(HisStatementItemDel record) {
		return hisStatementItemDelMapper.insertSelective(record);
	}

	public HisStatementItemDel selectByPrimaryKey(Long id) {
		return hisStatementItemDelMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(HisStatementItemDel record) {
		return hisStatementItemDelMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(HisStatementItemDel record) {
		return hisStatementItemDelMapper.updateByPrimaryKey(record);
	}

	public HisStatementItemDel selectOne(HisStatementItemDel record) {
		return hisStatementItemDelMapper.selectOne(record);
	}

	public List<HisStatementItemDel> selectList(HisStatementItemDel record) {
		return hisStatementItemDelMapper.selectList(record);
	}

	public int selectCount(HisStatementItemDel record) {
		return hisStatementItemDelMapper.selectCount(record);
	}

	public int deleteSelective(HisStatementItemDel record) {
		return hisStatementItemDelMapper.deleteSelective(record);
	}
}
