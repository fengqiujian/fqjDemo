package com.enjoyhis.persistence.his.dao;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisDepart;

public interface HisDepartMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(HisDepart record);

	int insertSelective(HisDepart record);

	HisDepart selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(HisDepart record);

	int updateByPrimaryKey(HisDepart record);

	HisDepart selectOne(HisDepart record);

	List<HisDepart> selectList(HisDepart record);

	int selectCount(HisDepart record);

	int deleteSelective(HisDepart record);
}