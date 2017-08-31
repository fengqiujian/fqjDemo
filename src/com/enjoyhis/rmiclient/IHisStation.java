package com.enjoyhis.rmiclient;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisStation;

public interface IHisStation {
	int modifyStatus(HisStation hisStation);

	int addStationInfo(HisStation hisStation);

	int updateStatus(HisStation hisStation);

	String checkById(int id);

	List<HisStation> pageToListJt(HisStation hisStation, int pageNumber, int pageSize);

	int findCountJt(HisStation hisStation);

	List<HisStation> findAllJt(HisStation hisStation);

	HisStation findById(int id);

	int modifyStationInfo(HisStation hisStation);

}
