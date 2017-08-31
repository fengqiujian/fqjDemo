package com.enjoyhis.persistence.his.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.po.HisDocroom;

@Service("hisDocroomDao")
public class HisDocroomDao {

	@Autowired
	HisDocroomMapper hisDocroomMapper;

	public int deleteByPrimaryKey(Integer id) {
		return hisDocroomMapper.deleteByPrimaryKey(id);
	}

	public int insert(HisDocroom record) {
		return hisDocroomMapper.insert(record);
	}

	public int insertSelective(HisDocroom record) {
		return hisDocroomMapper.insertSelective(record);
	}

	public HisDocroom selectByPrimaryKey(Integer id) {
		return hisDocroomMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(HisDocroom record) {
		return hisDocroomMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(HisDocroom record) {
		return hisDocroomMapper.updateByPrimaryKey(record);
	}

	public HisDocroom selectOne(HisDocroom record) {
		return hisDocroomMapper.selectOne(record);
	}

	public List<HisDocroom> selectList(HisDocroom record) {
		return hisDocroomMapper.selectList(record);
	}

	public int selectCount(HisDocroom record) {
		return hisDocroomMapper.selectCount(record);
	}

	public int deleteSelective(HisDocroom record) {
		return hisDocroomMapper.deleteSelective(record);
	}
}
