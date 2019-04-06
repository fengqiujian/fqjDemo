package com.enjoyhis.persistence.his.dao;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisPatientExt;

public interface HisPatientExtMapper {

	int deleteByPrimaryKey(Long patId);

	int insert(HisPatientExt record);

	int insertSelective(HisPatientExt record);

	HisPatientExt selectByPrimaryKey(Long patId);

	int updateByPrimaryKeySelective(HisPatientExt record);

	int updateByPrimaryKey(HisPatientExt record);

	HisPatientExt selectOne(HisPatientExt record);

	List<HisPatientExt> selectList(HisPatientExt record);

	int selectCount(HisPatientExt record);

	int deleteSelective(HisPatientExt record);
}