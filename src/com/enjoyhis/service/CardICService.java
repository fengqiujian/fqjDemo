package com.enjoyhis.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.dao.HisOrganizDao;
import com.enjoyhis.persistence.his.po.HisOrganiz;

@Service("cardICService")
public class CardICService {
	@Autowired
	HisOrganizDao hisOrganizDao;

	public List<HisOrganiz> getHisOrganizList() {
		HisOrganiz ho = new HisOrganiz();
		ho.setLevel(3);
		ho.setSqlStr(" and id <> 999");
		List<HisOrganiz> lists = hisOrganizDao.selectList(ho);
		List<HisOrganiz> list = new ArrayList<HisOrganiz>();
		for (HisOrganiz hos : lists) {
			ho = new HisOrganiz();
			ho.setId(hos.getId());
			ho.setUnitName(hos.getUnitName());
			list.add(ho);
		}
		return list;
	}
}
