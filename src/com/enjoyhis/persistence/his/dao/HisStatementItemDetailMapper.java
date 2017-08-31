package com.enjoyhis.persistence.his.dao;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisStatementItemDetail;

public interface HisStatementItemDetailMapper {

	int deleteByPrimaryKey(Long id);

	int insert(HisStatementItemDetail record);

	int insertSelective(HisStatementItemDetail record);

	HisStatementItemDetail selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(HisStatementItemDetail record);

	int updateByPrimaryKey(HisStatementItemDetail record);

	HisStatementItemDetail selectOne(HisStatementItemDetail record);

	List<HisStatementItemDetail> selectList(HisStatementItemDetail record);

	int selectCount(HisStatementItemDetail record);

	int deleteSelective(HisStatementItemDetail record);
}