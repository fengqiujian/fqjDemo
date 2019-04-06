package com.enjoyhis.persistence.his.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.po.HisStatementItem;

@Service("hisStatementItemDao")
public class HisStatementItemDao {

	@Autowired
	HisStatementItemMapper hisStatementItemMapper;

	public int deleteByPrimaryKey(Long id) {
		return hisStatementItemMapper.deleteByPrimaryKey(id);
	}

	public int insert(HisStatementItem record) {
		return hisStatementItemMapper.insert(record);
	}

	public int insertSelective(HisStatementItem record) {
		return hisStatementItemMapper.insertSelective(record);
	}

	public HisStatementItem selectByPrimaryKey(Long id) {
		return hisStatementItemMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(HisStatementItem record) {
		return hisStatementItemMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(HisStatementItem record) {
		return hisStatementItemMapper.updateByPrimaryKey(record);
	}

	public HisStatementItem selectOne(HisStatementItem record) {
		return hisStatementItemMapper.selectOne(record);
	}

	public List<HisStatementItem> selectList(HisStatementItem record) {
		return hisStatementItemMapper.selectList(record);
	}

	public int selectCount(HisStatementItem record) {
		return hisStatementItemMapper.selectCount(record);
	}

	public int deleteSelective(HisStatementItem record) {
		return hisStatementItemMapper.deleteSelective(record);
	}
}
