package com.enjoyhis.persistence.his.dao;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisRegisterLog;

public interface HisRegisterLogMapper {

	int deleteByPrimaryKey(Long id);

	int insert(HisRegisterLog record);

	int insertSelective(HisRegisterLog record);

	HisRegisterLog selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(HisRegisterLog record);

	int updateByPrimaryKey(HisRegisterLog record);

	HisRegisterLog selectOne(HisRegisterLog record);

	List<HisRegisterLog> selectList(HisRegisterLog record);

	int selectCount(HisRegisterLog record);

	int deleteSelective(HisRegisterLog record);
}