package com.enjoyhis.rmiclient;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisGroupEmployee;

public interface IHisGroupAssociatedRelevance {

	List<HisGroupEmployee> findGroupAssociatedRelevanceAll(HisGroupEmployee hisGroupEmployee);
	
	boolean upGroupEmployee(Long groupId,String names,Integer isdel);
}
