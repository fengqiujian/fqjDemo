package com.enjoyhis.rmiclient;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisDict;

public interface IHisDict {

	List<HisDict> page4ListTo(String iNames, String charges, HisDict hisDict, Integer pageNumber, Integer pageSize);

	Integer findCountTo(HisDict hisDict);

	List<HisDict> findDictAll(HisDict hisDict);

	int deleteDict(Integer id);

	HisDict findDictInfo(Integer id);

	int updatDictInfo(HisDict hisDict);

	int addDictInfo(HisDict hisDict);

}
