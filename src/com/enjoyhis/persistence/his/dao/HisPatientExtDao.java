package com.enjoyhis.persistence.his.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.po.HisPatientExt;

@Service("hisPatientExtDao")
public class HisPatientExtDao {

	@Autowired
	HisPatientExtMapper hisPatientExtMapper;

	public int deleteByPrimaryKey(Long patId) {
		return hisPatientExtMapper.deleteByPrimaryKey(patId);
	}

	public int insert(HisPatientExt record) {
		return hisPatientExtMapper.insert(record);
	}

	public int insertSelective(HisPatientExt record) {
		return hisPatientExtMapper.insertSelective(record);
	}

	public HisPatientExt selectByPrimaryKey(Long patId) {
		return hisPatientExtMapper.selectByPrimaryKey(patId);
	}

	public int updateByPrimaryKeySelective(HisPatientExt record) {
		return hisPatientExtMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(HisPatientExt record) {
		return hisPatientExtMapper.updateByPrimaryKey(record);
	}

	public HisPatientExt selectOne(HisPatientExt record) {
		return hisPatientExtMapper.selectOne(record);
	}

	public List<HisPatientExt> selectList(HisPatientExt record) {
		return hisPatientExtMapper.selectList(record);
	}

	public int selectCount(HisPatientExt record) {
		return hisPatientExtMapper.selectCount(record);
	}

	public int deleteSelective(HisPatientExt record) {
		return hisPatientExtMapper.deleteSelective(record);
	}
}
