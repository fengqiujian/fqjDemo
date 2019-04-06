package com.enjoyhis.rmiclient;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisOrganiz;

public interface IHisOrganiz {

	List<HisOrganiz> findOrganizAll(HisOrganiz hisOrganiz);

}
