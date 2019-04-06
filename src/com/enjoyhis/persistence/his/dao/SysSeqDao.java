package com.enjoyhis.persistence.his.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.po.SysSeq;

@Service("sysSeqDao")
public class SysSeqDao {

	@Autowired
	SysSeqMapper sysSeqMapper;

	public int deleteByPrimaryKey(Integer id) {
		return sysSeqMapper.deleteByPrimaryKey(id);
	}

	public int insert(SysSeq record) {
		return sysSeqMapper.insert(record);
	}

	public int insertSelective(SysSeq record) {
		return sysSeqMapper.insertSelective(record);
	}

	public SysSeq selectByPrimaryKey(Integer id) {
		return sysSeqMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(SysSeq record) {
		return sysSeqMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(SysSeq record) {
		return sysSeqMapper.updateByPrimaryKey(record);
	}

	public SysSeq selectOne(SysSeq record) {
		return sysSeqMapper.selectOne(record);
	}

	public List<SysSeq> selectList(SysSeq record) {
		return sysSeqMapper.selectList(record);
	}

	public int selectCount(SysSeq record) {
		return sysSeqMapper.selectCount(record);
	}

	public int deleteSelective(SysSeq record) {
		return sysSeqMapper.deleteSelective(record);
	}
}
