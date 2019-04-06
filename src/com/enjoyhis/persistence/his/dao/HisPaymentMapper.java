package com.enjoyhis.persistence.his.dao;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisPayment;

public interface HisPaymentMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(HisPayment record);

	int insertSelective(HisPayment record);

	HisPayment selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(HisPayment record);

	int updateByPrimaryKey(HisPayment record);

	HisPayment selectOne(HisPayment record);

	List<HisPayment> selectList(HisPayment record);

	int selectCount(HisPayment record);

	int deleteSelective(HisPayment record);
}