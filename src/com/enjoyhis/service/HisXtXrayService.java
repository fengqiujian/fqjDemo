package com.enjoyhis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.dao.HisXtXrayDao;
import com.enjoyhis.persistence.his.po.HisXtXray;

@Service("hisXtXrayService")
public class HisXtXrayService {

	@Autowired
	private HisXtXrayDao hisXtXrayDao;

	public HisXtXray findById(Integer idjt) {
		return hisXtXrayDao.selectByPrimaryKey(idjt);
	}

	public int updateDictInfo(HisXtXray entiyjt) {
		return hisXtXrayDao.updateByPrimaryKey(entiyjt);

	}

	public int addDictInfo(HisXtXray entiyjt) {
		return hisXtXrayDao.insert(entiyjt);

	}

}
