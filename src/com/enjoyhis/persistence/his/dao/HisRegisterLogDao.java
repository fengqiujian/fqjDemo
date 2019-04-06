package com.enjoyhis.persistence.his.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.po.HisRegisterLog;

@Service("hisRegisterLogDao")
public class HisRegisterLogDao {

	@Autowired
	HisRegisterLogMapper hisRegisterLogMapper;

	public int deleteByPrimaryKey(Long id) {
		return hisRegisterLogMapper.deleteByPrimaryKey(id);
	}

	public int insert(HisRegisterLog record) {
		return hisRegisterLogMapper.insert(record);
	}

	public int insertSelective(HisRegisterLog record) {
		return hisRegisterLogMapper.insertSelective(record);
	}

	public HisRegisterLog selectByPrimaryKey(Long id) {
		return hisRegisterLogMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(HisRegisterLog record) {
		return hisRegisterLogMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(HisRegisterLog record) {
		return hisRegisterLogMapper.updateByPrimaryKey(record);
	}

	public HisRegisterLog selectOne(HisRegisterLog record) {
		return hisRegisterLogMapper.selectOne(record);
	}

	public List<HisRegisterLog> selectList(HisRegisterLog record) {
		return hisRegisterLogMapper.selectList(record);
	}

	public int selectCount(HisRegisterLog record) {
		return hisRegisterLogMapper.selectCount(record);
	}

	public int deleteSelective(HisRegisterLog record) {
		return hisRegisterLogMapper.deleteSelective(record);
	}
}
