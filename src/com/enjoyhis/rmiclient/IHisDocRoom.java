package com.enjoyhis.rmiclient;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisDocroom;

public interface IHisDocRoom {
	List<HisDocroom> page4All(HisDocroom hisDocroom, Integer pageNumber, Integer pageSize);

	Integer findDocroomCount(HisDocroom hisDocroom);

	List<HisDocroom> findAllJtData(HisDocroom hisDocroom);

	int deleteDocRooms(Integer id);

	HisDocroom findByIdInfo(int parseInt);

	void addRooms(HisDocroom hisDocroom);

}
