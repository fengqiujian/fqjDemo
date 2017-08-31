package com.enjoyhis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.dao.HisPaymentDao;
import com.enjoyhis.persistence.his.po.HisPayment;

@Service("hisPaymentService")
public class HisPaymentService {

	@Autowired
	private HisPaymentDao hisPaymentDao;

	public HisPayment findById(Integer id) {
		return hisPaymentDao.selectByPrimaryKey(id);
	}

	public int updatePaymentInfo(HisPayment entiyjt) {
		return hisPaymentDao.updateByPrimaryKey(entiyjt);

	}

	public int addPaymentInfo(HisPayment entiyjt) {
		return hisPaymentDao.insertSelective(entiyjt);
	}
	
	public int deleteHisPayment(HisPayment entiyjt) {
		return hisPaymentDao.deleteSelective(entiyjt);
	}
	

}
