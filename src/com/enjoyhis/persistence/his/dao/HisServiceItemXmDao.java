package com.enjoyhis.persistence.his.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.po.HisServiceItemXm;

@Service("hisServiceItemXmDao")
public class HisServiceItemXmDao {

	@Autowired
	HisServiceItemXmMapper hisServiceItemXmMapper;

	public int deleteByPrimaryKey(String itemCode) {
		return hisServiceItemXmMapper.deleteByPrimaryKey(itemCode);
	}

	public int insert(HisServiceItemXm record) {
		return hisServiceItemXmMapper.insert(record);
	}

	public int insertSelective(HisServiceItemXm record) {
		return hisServiceItemXmMapper.insertSelective(record);
	}

	public HisServiceItemXm selectByPrimaryKey(String itemCode) {
		return hisServiceItemXmMapper.selectByPrimaryKey(itemCode);
	}

	public int updateByPrimaryKeySelective(HisServiceItemXm record) {
		return hisServiceItemXmMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(HisServiceItemXm record) {
		return hisServiceItemXmMapper.updateByPrimaryKey(record);
	}

	public HisServiceItemXm selectOne(HisServiceItemXm record) {
		return hisServiceItemXmMapper.selectOne(record);
	}

	public List<HisServiceItemXm> selectList(HisServiceItemXm record) {
		return hisServiceItemXmMapper.selectList(record);
	}

	public int selectCount(HisServiceItemXm record) {
		return hisServiceItemXmMapper.selectCount(record);
	}

	public int deleteSelective(HisServiceItemXm record) {
		return hisServiceItemXmMapper.deleteSelective(record);
	}
	public int insertAll (List<HisServiceItemXm> list){
		return hisServiceItemXmMapper.insertAll(list);
	}

	public int updateAll (List<HisServiceItemXm> list){
		return hisServiceItemXmMapper.updateAll(list);
	}
}
