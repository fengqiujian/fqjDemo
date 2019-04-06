package com.enjoyhis.persistence.his.dao;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisRegisterCall;

public interface HisRegisterCallMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(HisRegisterCall record);

	int insertSelective(HisRegisterCall record);

	HisRegisterCall selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(HisRegisterCall record);

	int updateByPrimaryKey(HisRegisterCall record);

	HisRegisterCall selectOne(HisRegisterCall record);

	List<HisRegisterCall> selectList(HisRegisterCall record);

	int selectCount(HisRegisterCall record);

	int deleteSelective(HisRegisterCall record);

	List<HisRegisterCall> selectListByGhids(List<Long> ghIds);

	int deleteAll();
}