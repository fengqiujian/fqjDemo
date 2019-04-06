package com.enjoyhis.rmiclient;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisGroup;

public interface IHisGroup {

	List<HisGroup> findGroupAll(HisGroup hisGroup);

}
