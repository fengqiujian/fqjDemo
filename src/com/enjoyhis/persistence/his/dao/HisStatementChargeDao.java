package com.enjoyhis.persistence.his.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.po.HisStatementCharge;
import com.enjoyhis.pojo.ReportSumPaymentData;

@Service("hisStatementChargeDao")
public class HisStatementChargeDao {

	@Autowired
	HisStatementChargeMapper hisStatementChargeMapper;

	public int deleteByPrimaryKey(Long id) {
		return hisStatementChargeMapper.deleteByPrimaryKey(id);
	}

	public int insert(HisStatementCharge record) {
		return hisStatementChargeMapper.insert(record);
	}

	public int insertSelective(HisStatementCharge record) {
		return hisStatementChargeMapper.insertSelective(record);
	}

	public HisStatementCharge selectByPrimaryKey(Long id) {
		return hisStatementChargeMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(HisStatementCharge record) {
		return hisStatementChargeMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(HisStatementCharge record) {
		return hisStatementChargeMapper.updateByPrimaryKey(record);
	}

	public HisStatementCharge selectOne(HisStatementCharge record) {
		return hisStatementChargeMapper.selectOne(record);
	}

	public List<HisStatementCharge> selectList(HisStatementCharge record) {
		return hisStatementChargeMapper.selectList(record);
	}

	public int selectCount(HisStatementCharge record) {
		return hisStatementChargeMapper.selectCount(record);
	}

	public int deleteSelective(HisStatementCharge record) {
		return hisStatementChargeMapper.deleteSelective(record);
	}
	public List<ReportSumPaymentData> selectDateList(HisStatementCharge record){
		return hisStatementChargeMapper.selectDateList(record);
	}
}
