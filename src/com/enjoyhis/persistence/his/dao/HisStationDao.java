package com.enjoyhis.persistence.his.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.po.HisStation;

@Service("hisStationDao")
public class HisStationDao {

	@Autowired
	HisStationMapper hisStationMapper;

	public int deleteByPrimaryKey(Integer id) {
		return hisStationMapper.deleteByPrimaryKey(id);
	}

	public int insert(HisStation record) {
		return hisStationMapper.insert(record);
	}

	public int insertSelective(HisStation record) {
		return hisStationMapper.insertSelective(record);
	}

	public HisStation selectByPrimaryKey(Integer id) {
		return hisStationMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(HisStation record) {
		return hisStationMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(HisStation record) {
		return hisStationMapper.updateByPrimaryKey(record);
	}

	public HisStation selectOne(HisStation record) {
		return hisStationMapper.selectOne(record);
	}

	public List<HisStation> selectList(HisStation record) {
		return hisStationMapper.selectList(record);
	}

	public int selectCount(HisStation record) {
		return hisStationMapper.selectCount(record);
	}

	public int deleteSelective(HisStation record) {
		return hisStationMapper.deleteSelective(record);
	}
}
