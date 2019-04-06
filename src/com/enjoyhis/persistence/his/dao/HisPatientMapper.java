package com.enjoyhis.persistence.his.dao;

import java.util.List;
import java.util.Map;

import com.enjoyhis.persistence.his.po.HisPatient;

public interface HisPatientMapper {

	int deleteByPrimaryKey(Long id);

	int insert(HisPatient record);

	int insertSelective(HisPatient record);

	HisPatient selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(HisPatient record);

	int updateByPrimaryKey(HisPatient record);

	HisPatient selectOne(HisPatient record);

	List<HisPatient> selectList(HisPatient record);

	int selectCount(HisPatient record);

	int deleteSelective(HisPatient record);

	List<Object> page4List(Map<String, Object> map);

	Integer page4Count(Map<String, Object> map);

	List<Object> patientPage4List(Map<String, Object> map);

	Integer patientPage4Count(Map<String, Object> map);
}