package com.enjoyhis.persistence.his.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.po.HisPatient;

@Service("hisPatientDao")
public class HisPatientDao {

	@Autowired
	HisPatientMapper hisPatientMapper;

	public int deleteByPrimaryKey(Long id) {
		return hisPatientMapper.deleteByPrimaryKey(id);
	}

	public int insert(HisPatient record) {
		return hisPatientMapper.insert(record);
	}

	public int insertSelective(HisPatient record) {
		return hisPatientMapper.insertSelective(record);
	}

	public HisPatient selectByPrimaryKey(Long id) {
		return hisPatientMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(HisPatient record) {
		return hisPatientMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(HisPatient record) {
		return hisPatientMapper.updateByPrimaryKey(record);
	}

	public HisPatient selectOne(HisPatient record) {
		return hisPatientMapper.selectOne(record);
	}

	public List<HisPatient> selectList(HisPatient record) {
		return hisPatientMapper.selectList(record);
	}

	public int selectCount(HisPatient record) {
		return hisPatientMapper.selectCount(record);
	}

	public int deleteSelective(HisPatient record) {
		return hisPatientMapper.deleteSelective(record);
	}

	public List<Object> page4List(Map<String, Object> map) {
		return hisPatientMapper.page4List(map);
	}

	public Integer page4Count(Map<String, Object> map) {
		return hisPatientMapper.page4Count(map);
	}

	public List<Object> patientPage4List(Map<String, Object> map) {
		return hisPatientMapper.patientPage4List(map);
	}

	public Integer patientPage4Count(Map<String, Object> map) {
		return hisPatientMapper.patientPage4Count(map);
	}

}
