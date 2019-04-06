package com.enjoyhis.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.dao.HisStationDao;
import com.enjoyhis.persistence.his.po.HisStation;

/**
 * 
 * @Name: HisStationService
 * @Description: 岗位操作
 * @Author: 作者
 * @Version:（版本号）
 * @Create Date: 创建日期
 */
@Service("hisStationService")
public class HisStationService {

	@Autowired
	private HisStationDao hisStationDao;

	public List<HisStation> page4List(HisStation hisStation, Integer pageNumber, Integer pageSize) {
		hisStation.setSqlSort("id asc");
		hisStation.setSqlStr(" and status=1 ");
		hisStation.setLimitCount(pageSize);
		Long limitStart = (long) (pageSize * (pageNumber - 1));
		hisStation.setLimitStart(limitStart);
		return hisStationDao.selectList(hisStation);
	}
	
	public Map<Integer,String> getStationName(){
		List<HisStation> list = hisStationDao.selectList(new HisStation());
		Map<Integer,String> map = new HashMap<>();
		for(HisStation ar : list){
			map.put(ar.getId(), ar.getGwName());
		}
		return map;
	}

	// 统计ID
	public Integer findCount(HisStation hisStation) {
//		hisStation.setSqlStr("status=1");
		return hisStationDao.selectCount(hisStation);
	}

	// 通过ID查询信息
	public HisStation findById(Integer id) {
		return hisStationDao.selectByPrimaryKey(id);
	}

	// 更新数据
	public int updatAddStationFy(HisStation hisStation) {
		return hisStationDao.updateByPrimaryKey(hisStation);
	}

	// 添加数据
	public int addDepartFy(HisStation hisStation) {
		return hisStationDao.insertSelective(hisStation);
	}

	public int deleteStationInfo(HisStation hisStation) {
		return hisStationDao.deleteSelective(hisStation);
	}
}
