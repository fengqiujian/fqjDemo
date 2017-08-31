package com.enjoyhis.persistence.his.dao;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisPosDetails;

public interface HisPosDetailsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HisPosDetails record);

    int insertSelective(HisPosDetails record);

    HisPosDetails selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisPosDetails record);

    int updateByPrimaryKey(HisPosDetails record);
    
    HisPosDetails selectOne(HisPosDetails record);

	List<HisPosDetails> selectList(HisPosDetails record);

	int selectCount(HisPosDetails record);

	int deleteSelective(HisPosDetails record);
}