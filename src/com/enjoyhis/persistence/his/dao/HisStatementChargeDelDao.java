package com.enjoyhis.persistence.his.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.po.HisStatementChargeDel;

@Service("hisStatementChargeDelDao")
public class HisStatementChargeDelDao {

	@Autowired
	HisStatementChargeDelMapper hisStatementChargeDelMapper;

	public int deleteByPrimaryKey(Long id) {
		return hisStatementChargeDelMapper.deleteByPrimaryKey(id);
	}

	public int insert(HisStatementChargeDel record) {
		return hisStatementChargeDelMapper.insert(record);
	}

	public int insertSelective(HisStatementChargeDel record) {
		return hisStatementChargeDelMapper.insertSelective(record);
	}

	public HisStatementChargeDel selectByPrimaryKey(Long id) {
		return hisStatementChargeDelMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(HisStatementChargeDel record) {
		return hisStatementChargeDelMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(HisStatementChargeDel record) {
		return hisStatementChargeDelMapper.updateByPrimaryKey(record);
	}

	public HisStatementChargeDel selectOne(HisStatementChargeDel record) {
		return hisStatementChargeDelMapper.selectOne(record);
	}

	public List<HisStatementChargeDel> selectList(HisStatementChargeDel record) {
		return hisStatementChargeDelMapper.selectList(record);
	}

	public int selectCount(HisStatementChargeDel record) {
		return hisStatementChargeDelMapper.selectCount(record);
	}

	public int deleteSelective(HisStatementChargeDel record) {
		return hisStatementChargeDelMapper.deleteSelective(record);
	}
}
