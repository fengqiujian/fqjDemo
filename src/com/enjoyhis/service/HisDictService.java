package com.enjoyhis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.dao.HisDictDao;
import com.enjoyhis.persistence.his.po.HisDict;

@Service("hisDictService")
public class HisDictService {
	@Autowired
	private HisDictDao hisDictDao;

	public HisDict findById(Integer id) {
		return hisDictDao.selectByPrimaryKey(id);
	}

	public List<HisDict> page4List(HisDict hisDict, Integer pageNumber, Integer pageSize) {
		hisDict.setSqlSort("id desc");
		hisDict.setLimitCount(pageSize);
		Long limitStart = (long) (pageSize * (pageNumber - 1));
		hisDict.setLimitStart(limitStart);
		return hisDictDao.selectList(hisDict);
	}

	public Integer findCount(HisDict hisDict) {
		return hisDictDao.selectCount(hisDict);
	}

	public int updateDictInfo(HisDict entiyjt) {
		return hisDictDao.updateByPrimaryKey(entiyjt);
	}

	public int addDictInfo(HisDict entiyjt) {
		return hisDictDao.insertSelective(entiyjt);
	}

	public void delectNoid(HisDict hisDict, Integer idjt) {
		hisDict.setSqlStr("id" + "!='" + idjt + "'");
		hisDictDao.deleteSelective(hisDict);
	}

	public int deleteDictAll(HisDict hisDict) {
		// TODO Auto-generated method stub
		return hisDictDao.deleteSelective(hisDict);
	}

}
