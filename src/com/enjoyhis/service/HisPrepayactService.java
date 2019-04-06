package com.enjoyhis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.dao.HisPrepayactDao;
import com.enjoyhis.persistence.his.po.HisPrepayact;

@Service("hisPrepayactService")
public class HisPrepayactService {

	@Autowired
	private HisPrepayactDao hisPrepayactDao;

	public List<HisPrepayact> page4List(HisPrepayact hisPrepayact, String aname, Integer pageNumber, Integer pageSize) {
		hisPrepayact.setSqlSort("id asc");
		hisPrepayact.setSqlStr(" and status=1 ");
		if (aname != null && aname != "") {
			hisPrepayact.setSqlStr("and actname LIKE '%" + aname + "%'");
		}
		hisPrepayact.setLimitCount(pageSize);
		Long limitStart = (long) (pageSize * (pageNumber - 1));
		hisPrepayact.setLimitStart(limitStart);
		return hisPrepayactDao.selectList(hisPrepayact);
	}

	public Integer findCount(HisPrepayact hisPrepayact) {
		hisPrepayact.setSqlStr("status=1");
		return hisPrepayactDao.selectCount(hisPrepayact);
	}

	// 查询数据
	public HisPrepayact findById(Integer idjt) {
		return hisPrepayactDao.selectByPrimaryKey(idjt);
	}

	// 更新数据
	public int UpdateaddPrepayactFy(HisPrepayact entiyjt) {
		return hisPrepayactDao.updateByPrimaryKey(entiyjt);
	}

	// 添加数据
	public int addPrepayactFy(HisPrepayact entiyjt) {
		return hisPrepayactDao.insertSelective(entiyjt);
	}

	public void addActivityInfo(HisPrepayact hisPrepayact) {
		hisPrepayactDao.insert(hisPrepayact);
	}

	// According to the ID to delete information
	public void delByIdPrepayact(Integer id) {
		hisPrepayactDao.deleteByPrimaryKey(id);
	}

	// 校验ID
	public String checkById(int parseInt) {
		HisPrepayact sizeHisPrepayact = hisPrepayactDao.selectByPrimaryKey(parseInt);
		String flag = "";
		if (sizeHisPrepayact != null) {
			flag = "1";
		} else {
			flag = "2";
		}
		return flag;
	}

	public int updateStatus(HisPrepayact hisPrepayact) {
		return hisPrepayactDao.updateByPrimaryKey(hisPrepayact);
	}

	public int deletePrepayactAll(HisPrepayact hisPrepayact) {
		// TODO Auto-generated method stub
		return hisPrepayactDao.deleteSelective(hisPrepayact);
	}

}
