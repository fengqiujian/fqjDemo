package com.enjoyhis.persistence.his.dao;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisPatientCase;

public interface HisPatientCaseMapper {

	int deleteByPrimaryKey(Long patId);

	int insert(HisPatientCase record);

	int insertSelective(HisPatientCase record);

	HisPatientCase selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(HisPatientCase record);

	int updateByPrimaryKey(HisPatientCase record);

	HisPatientCase selectOne(HisPatientCase record);

	List<HisPatientCase> selectList(HisPatientCase record);

	int selectCount(HisPatientCase record);

	int deleteSelective(HisPatientCase record);
}