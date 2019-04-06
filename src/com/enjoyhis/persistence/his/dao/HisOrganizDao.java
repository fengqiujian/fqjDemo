package com.enjoyhis.persistence.his.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.po.HisOrganiz;

@Service("hisOrganizDao")
public class HisOrganizDao {

	@Autowired
	HisOrganizMapper hisOrganizMapper;

	public int deleteByPrimaryKey(Integer id) {
		return hisOrganizMapper.deleteByPrimaryKey(id);
	}

	public int insert(HisOrganiz record) {
		return hisOrganizMapper.insert(record);
	}

	public int insertSelective(HisOrganiz record) {
		return hisOrganizMapper.insertSelective(record);
	}

	public HisOrganiz selectByPrimaryKey(Integer id) {
		return hisOrganizMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(HisOrganiz record) {
		return hisOrganizMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(HisOrganiz record) {
		return hisOrganizMapper.updateByPrimaryKey(record);
	}

	public HisOrganiz selectOne(HisOrganiz record) {
		return hisOrganizMapper.selectOne(record);
	}

	public List<HisOrganiz> selectList(HisOrganiz record) {
		return hisOrganizMapper.selectList(record);
	}

	public int selectCount(HisOrganiz record) {
		return hisOrganizMapper.selectCount(record);
	}

	public int deleteSelective(HisOrganiz record) {
		return hisOrganizMapper.deleteSelective(record);
	}
}
