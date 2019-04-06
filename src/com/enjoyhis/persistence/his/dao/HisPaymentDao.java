package com.enjoyhis.persistence.his.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.po.HisPayment;

@Service("hisPaymentDao")
public class HisPaymentDao {

	@Autowired
	HisPaymentMapper hisPaymentMapper;

	public int deleteByPrimaryKey(Integer id) {
		return hisPaymentMapper.deleteByPrimaryKey(id);
	}

	public int insert(HisPayment record) {
		return hisPaymentMapper.insert(record);
	}

	public int insertSelective(HisPayment record) {
		return hisPaymentMapper.insertSelective(record);
	}

	public HisPayment selectByPrimaryKey(Integer id) {
		return hisPaymentMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(HisPayment record) {
		return hisPaymentMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(HisPayment record) {
		return hisPaymentMapper.updateByPrimaryKey(record);
	}

	public HisPayment selectOne(HisPayment record) {
		return hisPaymentMapper.selectOne(record);
	}

	public List<HisPayment> selectList(HisPayment record) {
		return hisPaymentMapper.selectList(record);
	}

	public int selectCount(HisPayment record) {
		return hisPaymentMapper.selectCount(record);
	}

	public int deleteSelective(HisPayment record) {
		return hisPaymentMapper.deleteSelective(record);
	}
}
