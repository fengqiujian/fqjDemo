package com.enjoyhis.persistence.his.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.po.HisPosDetails;

@Service("hisPosDetailsDao")
public class HisPosDetailsDao {

	@Autowired
	HisPosDetailsMapper hisPosDetailsMapper;

	public int deleteByPrimaryKey(Long id) {
		return hisPosDetailsMapper.deleteByPrimaryKey(id);
	}

	public int insert(HisPosDetails record) {
		return hisPosDetailsMapper.insert(record);
	}

	public int insertSelective(HisPosDetails record) {
		return hisPosDetailsMapper.insertSelective(record);
	}

	public HisPosDetails selectByPrimaryKey(Long id) {
		return hisPosDetailsMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(HisPosDetails record) {
		return hisPosDetailsMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(HisPosDetails record) {
		return hisPosDetailsMapper.updateByPrimaryKey(record);
	}

	public HisPosDetails selectOne(HisPosDetails record) {
		return hisPosDetailsMapper.selectOne(record);
	}

	public List<HisPosDetails> selectList(HisPosDetails record) {
		return hisPosDetailsMapper.selectList(record);
	}

	public int selectCount(HisPosDetails record) {
		return hisPosDetailsMapper.selectCount(record);
	}

	public int deleteSelective(HisPosDetails record) {
		return hisPosDetailsMapper.deleteSelective(record);
	}
}
