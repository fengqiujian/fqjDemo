package com.enjoyhis.persistence.his.dao;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisStatementChargeDel;

public interface HisStatementChargeDelMapper {

	int deleteByPrimaryKey(Long id);

	int insert(HisStatementChargeDel record);

	int insertSelective(HisStatementChargeDel record);

	HisStatementChargeDel selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(HisStatementChargeDel record);

	int updateByPrimaryKey(HisStatementChargeDel record);

	HisStatementChargeDel selectOne(HisStatementChargeDel record);

	List<HisStatementChargeDel> selectList(HisStatementChargeDel record);

	int selectCount(HisStatementChargeDel record);

	int deleteSelective(HisStatementChargeDel record);
}