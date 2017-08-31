package com.enjoyhis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.dao.HisStatementDao;
import com.enjoyhis.persistence.his.po.HisStatement;

@Service("hisStatementService")
public class HisStatementService {
	@Autowired
	private HisStatementDao hisStatementDao;

	public HisStatement getHisStamentInfo(long id) {
		return hisStatementDao.selectByPrimaryKey(id);
	}

}
