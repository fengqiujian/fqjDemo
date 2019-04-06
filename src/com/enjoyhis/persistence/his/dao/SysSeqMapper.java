package com.enjoyhis.persistence.his.dao;

import java.util.List;

import com.enjoyhis.persistence.his.po.SysSeq;

public interface SysSeqMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(SysSeq record);

	int insertSelective(SysSeq record);

	SysSeq selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SysSeq record);

	int updateByPrimaryKey(SysSeq record);

	SysSeq selectOne(SysSeq record);

	List<SysSeq> selectList(SysSeq record);

	int selectCount(SysSeq record);

	int deleteSelective(SysSeq record);
}