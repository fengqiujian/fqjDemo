package com.enjoyhis.rmiclient;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisPatientCase;

public interface HesPatientCaseService {

	boolean insertPatientCase2jt(HisPatientCase hisPatientCase);

	List<HisPatientCase> getPatientCaseByPid(Long pid);

	List<HisPatientCase> getHisPatientList();
	
	HisPatientCase selectOne(HisPatientCase po);
	
	

}
