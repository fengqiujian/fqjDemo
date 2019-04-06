package com.enjoyhis.rmiclient;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisDepart;

public interface IHisDepart {
	List<HisDepart> findAllJt(HisDepart hisDepart);

	int findDepartInfo(HisDepart hisDepart);

	int addDepartInfo(HisDepart hisDepart);

	String checkById(int id);

	HisDepart modifyDepartInfo(Integer id);

	int updateDepartInfo(HisDepart hisDepart);

	HisDepart findDepart(Integer id);

	int findIdToDepart(HisDepart hisDepart);

	List<HisDepart> page4All(String dName, HisDepart hisDepart, Integer pageNumber, Integer pageSize);
}
