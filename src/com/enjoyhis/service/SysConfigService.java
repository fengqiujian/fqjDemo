package com.enjoyhis.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.dao.SysConfigMapper;
import com.enjoyhis.persistence.his.po.SysConfig;

@Service("sysConfigService")
public class SysConfigService {

	@Autowired
	SysConfigMapper sysConfigMapper;

	public static Map<String, String> cm = new HashMap<String, String>();
	public static List<SysConfig> scList = new ArrayList<SysConfig>();

	public Map<String, String> getSysConfig() {
		if (cm != null && cm.size() > 0) {
			return cm;
		}

		SysConfig record = new SysConfig();
		List<SysConfig> list = sysConfigMapper.selectList(record);
		for (SysConfig sysConfig : list) {
			cm.put(sysConfig.getKeystr(), sysConfig.getValuestr());
		}
		return cm;
	}

	public List<SysConfig> getSysConfigList() {
		// if (scList != null && scList.size() > 0) {
		// return scList;
		// }
		SysConfig record = new SysConfig();
		record.setSqlStr(" id > 7");
		scList = sysConfigMapper.selectList(record);
		return scList;
	}
	// -----------------------------------

	public SysConfig findByIdInfo(Integer idjt) {
		return sysConfigMapper.selectByPrimaryKey(idjt);
	}

	public int updateSysConfigToFy(SysConfig entiyjt) {
		return sysConfigMapper.updateByPrimaryKeySelective(entiyjt);
	}

	public int addSysConfigFy(SysConfig entiyjt) {
		return sysConfigMapper.insertSelective(entiyjt);
	}

}
