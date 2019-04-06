package com.enjoyhis.rmiclient;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisEmployee;

public interface IHisEmployee {
	List<HisEmployee> addEmployeeJt(HisEmployee hisEmployee);

	List<HisEmployee> findAllJt(HisEmployee hisEmployee);

	int updateEmployee(HisEmployee entiyjt);

	int addEmployeeFy(HisEmployee entiyjt);

	String checkById(Long id);
}
