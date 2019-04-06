package com.enjoyhis.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.dao.HisDepartDao;
import com.enjoyhis.persistence.his.po.HisDepart;

@Service("hisDepartService")
public class HisDepartService {

	@Autowired
	private HisDepartDao hisDepartDao;

	public HisDepart findById(Integer id) {
		return hisDepartDao.selectByPrimaryKey(id);
	}
	
	public Map<Integer,String> getDepartName(){
		List<HisDepart> list = hisDepartDao.selectList(new HisDepart());
		Map<Integer,String> map = new HashMap<>();
		for(HisDepart ar : list){
			map.put(ar.getId(), ar.getDepName());
		}
		return map;
	}

	public List<HisDepart> page4List(HisDepart hisDepart, Integer pageNumber, Integer pageSize) {
		hisDepart.setSqlSort("id asc");
		hisDepart.setSqlStr(" and status=1 ");
		hisDepart.setLimitCount(pageSize);
		Long limitStart = (long) (pageSize * (pageNumber - 1));
		hisDepart.setLimitStart(limitStart);
		return hisDepartDao.selectList(hisDepart);
	}

	public Integer findCount(HisDepart hisDepart) {
//		hisDepart.setSqlStr("status=1");
		return hisDepartDao.selectCount(hisDepart);
	}

	// 更新数据
	public int updatAddDepartFy(HisDepart hisDepart) {
		return hisDepartDao.updateByPrimaryKey(hisDepart);
	}

	// 添加数据
	public int addDepartFy(HisDepart hisDepart) {
		return hisDepartDao.insertSelective(hisDepart);
	}

	public int deleteDepartAll(HisDepart hisDepart) {
		return hisDepartDao.deleteSelective(hisDepart);
	}
}
