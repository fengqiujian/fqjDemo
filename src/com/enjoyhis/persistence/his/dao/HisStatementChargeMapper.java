package com.enjoyhis.persistence.his.dao;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisStatementCharge;
import com.enjoyhis.pojo.ReportSumPaymentData;

public interface HisStatementChargeMapper {

	int deleteByPrimaryKey(Long id);

	int insert(HisStatementCharge record);

	int insertSelective(HisStatementCharge record);

	HisStatementCharge selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(HisStatementCharge record);

	int updateByPrimaryKey(HisStatementCharge record);

	HisStatementCharge selectOne(HisStatementCharge record);

	List<HisStatementCharge> selectList(HisStatementCharge record);

	int selectCount(HisStatementCharge record);

	int deleteSelective(HisStatementCharge record);
	
	List<ReportSumPaymentData> selectDateList(HisStatementCharge record);
}