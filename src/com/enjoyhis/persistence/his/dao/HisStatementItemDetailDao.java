package com.enjoyhis.persistence.his.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.po.HisStatementItemDetail;

@Service("hisStatementItemDetailDao")
public class HisStatementItemDetailDao {

	@Autowired
	HisStatementItemDetailMapper hisStatementItemDetailMapper;

	public int deleteByPrimaryKey(Long id) {
		return hisStatementItemDetailMapper.deleteByPrimaryKey(id);
	}

	public int insert(HisStatementItemDetail record) {
		return hisStatementItemDetailMapper.insert(record);
	}

	public int insertSelective(HisStatementItemDetail record) {
		return hisStatementItemDetailMapper.insertSelective(record);
	}

	public HisStatementItemDetail selectByPrimaryKey(Long id) {
		return hisStatementItemDetailMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(HisStatementItemDetail record) {
		return hisStatementItemDetailMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(HisStatementItemDetail record) {
		return hisStatementItemDetailMapper.updateByPrimaryKey(record);
	}

	public HisStatementItemDetail selectOne(HisStatementItemDetail record) {
		return hisStatementItemDetailMapper.selectOne(record);
	}

	public List<HisStatementItemDetail> selectList(HisStatementItemDetail record) {
		return hisStatementItemDetailMapper.selectList(record);
	}

	public int selectCount(HisStatementItemDetail record) {
		return hisStatementItemDetailMapper.selectCount(record);
	}

	public int deleteSelective(HisStatementItemDetail record) {
		return hisStatementItemDetailMapper.deleteSelective(record);
	}
}
