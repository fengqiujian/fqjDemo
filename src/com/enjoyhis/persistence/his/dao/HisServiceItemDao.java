package com.enjoyhis.persistence.his.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.po.HisServiceItem;

@Service("hisServiceItemDao")
public class HisServiceItemDao {

	@Autowired
	HisServiceItemMapper hisServiceItemMapper;

	public int deleteByPrimaryKey(String itemCode) {
		return hisServiceItemMapper.deleteByPrimaryKey(itemCode);
	}

	public int insert(HisServiceItem record) {
		return hisServiceItemMapper.insert(record);
	}

	public int insertSelective(HisServiceItem record) {
		return hisServiceItemMapper.insertSelective(record);
	}

	public HisServiceItem selectByPrimaryKey(String itemCode) {
		return hisServiceItemMapper.selectByPrimaryKey(itemCode);
	}

	public int updateByPrimaryKeySelective(HisServiceItem record) {
		return hisServiceItemMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(HisServiceItem record) {
		return hisServiceItemMapper.updateByPrimaryKey(record);
	}

	public HisServiceItem selectOne(HisServiceItem record) {
		return hisServiceItemMapper.selectOne(record);
	}

	public List<HisServiceItem> selectList(HisServiceItem record) {
		return hisServiceItemMapper.selectList(record);
	}

	public int selectCount(HisServiceItem record) {
		return hisServiceItemMapper.selectCount(record);
	}

	public int deleteSelective(HisServiceItem record) {
		return hisServiceItemMapper.deleteSelective(record);
	}
}
