package com.enjoyhis.persistence.his.dao;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisOrganiz;

public interface HisOrganizMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(HisOrganiz record);

	int insertSelective(HisOrganiz record);

	HisOrganiz selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(HisOrganiz record);

	int updateByPrimaryKey(HisOrganiz record);

	HisOrganiz selectOne(HisOrganiz record);

	List<HisOrganiz> selectList(HisOrganiz record);

	int selectCount(HisOrganiz record);

	int deleteSelective(HisOrganiz record);
}