package com.enjoyhis.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.dao.HisOrganizDao;
import com.enjoyhis.persistence.his.po.HisOrganiz;

@Service("hisOrganizService")
public class HisOrganizService {

	@Autowired
	HisOrganizDao hisOrganizDao;

	public List<HisOrganiz> getList(HisOrganiz rddi) {
		return hisOrganizDao.selectList(rddi);
	}

	public String getOneOfName(Integer i) {
		HisOrganiz rddi = new HisOrganiz();
		rddi.setId(i);
		return hisOrganizDao.selectOne(rddi).getUnitName();
	}

	public Map<String, String> getMap() {
		HisOrganiz rddi = new HisOrganiz();
		List<HisOrganiz> list = hisOrganizDao.selectList(rddi);
		Map<String, String> map = new HashMap<>();
		for (HisOrganiz ho : list) {
			map.put(ho.getId().toString(), ho.getUnitName());
		}
		return map;
	}

	public int updateDictInfo(HisOrganiz entiyjt) {
		return hisOrganizDao.updateByPrimaryKey(entiyjt);
	}

	public int addDictInfo(HisOrganiz entiyjt) {
		return hisOrganizDao.insertSelective(entiyjt);

	}

	public List<HisOrganiz> findOrganiz(HisOrganiz hisOrganiz) {
		return hisOrganizDao.selectList(hisOrganiz);
	}

	public HisOrganiz findById(Integer id) {
		return hisOrganizDao.selectByPrimaryKey(id);
	}

	public HisOrganiz findByIdArr(HisOrganiz hisOrganiz) {

		return hisOrganizDao.selectOne(hisOrganiz);
	}

	public List<HisOrganiz> findByIds(HisOrganiz hisOrganiz) {
		return hisOrganizDao.selectList(hisOrganiz);
	}
	
	public int deleteHisOrganiz(HisOrganiz ho ){
		return hisOrganizDao.deleteSelective(ho);
	}
}
