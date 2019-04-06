package com.enjoyhis.persistence.his.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.po.HisPatientCase;

@Service("hisPatientCaseDao")
public class HisPatientCaseDao {

	@Autowired
	HisPatientCaseMapper hisPatientCaseMapper;

	public int deleteByPrimaryKey(Long patId) {
		return hisPatientCaseMapper.deleteByPrimaryKey(patId);
	}

	public int insert(HisPatientCase record) {
		return hisPatientCaseMapper.insert(record);
	}

	public int insertSelective(HisPatientCase record) {
		return hisPatientCaseMapper.insertSelective(record);
	}

	public HisPatientCase selectByPrimaryKey(Long patId) {
		return hisPatientCaseMapper.selectByPrimaryKey(patId);
	}

	public int updateByPrimaryKeySelective(HisPatientCase record) {
		return hisPatientCaseMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(HisPatientCase record) {
		return hisPatientCaseMapper.updateByPrimaryKey(record);
	}

	public HisPatientCase selectOne(HisPatientCase record) {
		return hisPatientCaseMapper.selectOne(record);
	}

	public List<HisPatientCase> selectList(HisPatientCase record) {
		return hisPatientCaseMapper.selectList(record);
	}

	public int selectCount(HisPatientCase record) {
		return hisPatientCaseMapper.selectCount(record);
	}

	public int deleteSelective(HisPatientCase record) {
		return hisPatientCaseMapper.deleteSelective(record);
	}
}
