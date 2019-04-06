package com.enjoyhis.persistence.his.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.po.HisServiceItemFl;

@Service("hisServiceItemFlDao")
public class HisServiceItemFlDao {

	@Autowired
	HisServiceItemFlMapper hisServiceItemFlMapper;

	public int deleteByPrimaryKey(String itemCode) {
		return hisServiceItemFlMapper.deleteByPrimaryKey(itemCode);
	}

	public int insert(HisServiceItemFl record) {
		return hisServiceItemFlMapper.insert(record);
	}

	public int insertSelective(HisServiceItemFl record) {
		return hisServiceItemFlMapper.insertSelective(record);
	}

	public HisServiceItemFl selectByPrimaryKey(String itemCode) {
		return hisServiceItemFlMapper.selectByPrimaryKey(itemCode);
	}

	public int updateByPrimaryKeySelective(HisServiceItemFl record) {
		return hisServiceItemFlMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(HisServiceItemFl record) {
		return hisServiceItemFlMapper.updateByPrimaryKey(record);
	}

	public HisServiceItemFl selectOne(HisServiceItemFl record) {
		return hisServiceItemFlMapper.selectOne(record);
	}

	public List<HisServiceItemFl> selectList(HisServiceItemFl record) {
		return hisServiceItemFlMapper.selectList(record);
	}

	public int selectCount(HisServiceItemFl record) {
		return hisServiceItemFlMapper.selectCount(record);
	}

	public int deleteSelective(HisServiceItemFl record) {
		return hisServiceItemFlMapper.deleteSelective(record);
	}
}
