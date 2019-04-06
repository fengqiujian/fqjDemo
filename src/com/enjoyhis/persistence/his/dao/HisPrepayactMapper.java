package com.enjoyhis.persistence.his.dao;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisPrepayact;

public interface HisPrepayactMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(HisPrepayact record);

	int insertSelective(HisPrepayact record);

	HisPrepayact selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(HisPrepayact record);

	int updateByPrimaryKey(HisPrepayact record);

	HisPrepayact selectOne(HisPrepayact record);

	List<HisPrepayact> selectList(HisPrepayact record);

	int selectCount(HisPrepayact record);

	int deleteSelective(HisPrepayact record);
}