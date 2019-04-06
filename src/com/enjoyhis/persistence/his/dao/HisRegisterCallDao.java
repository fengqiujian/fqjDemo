package com.enjoyhis.persistence.his.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.po.HisRegisterCall;

@Service("hisRegisterCallDao")
public class HisRegisterCallDao {

	@Autowired
	HisRegisterCallMapper hisRegisterCallMapper;

	public int deleteByPrimaryKey(Integer id) {
		return hisRegisterCallMapper.deleteByPrimaryKey(id);
	}

	public int insert(HisRegisterCall record) {
		return hisRegisterCallMapper.insert(record);
	}

	public int insertSelective(HisRegisterCall record) {
		return hisRegisterCallMapper.insertSelective(record);
	}

	public HisRegisterCall selectByPrimaryKey(Integer id) {
		return hisRegisterCallMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(HisRegisterCall record) {
		return hisRegisterCallMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(HisRegisterCall record) {
		return hisRegisterCallMapper.updateByPrimaryKey(record);
	}

	public HisRegisterCall selectOne(HisRegisterCall record) {
		return hisRegisterCallMapper.selectOne(record);
	}

	public List<HisRegisterCall> selectList(HisRegisterCall record) {
		return hisRegisterCallMapper.selectList(record);
	}

	public int selectCount(HisRegisterCall record) {
		return hisRegisterCallMapper.selectCount(record);
	}

	public int deleteSelective(HisRegisterCall record) {
		return hisRegisterCallMapper.deleteSelective(record);
	}

	public List<HisRegisterCall> selectListByGhids(List<Long> ghIds) {
		return hisRegisterCallMapper.selectListByGhids(ghIds);
	}
}
