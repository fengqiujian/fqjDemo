package com.enjoyhis.rmiclient;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisServiceItemFl;

public interface IHisServiceItemFl {

	List<HisServiceItemFl> findAllJt(HisServiceItemFl hisServiceItemFl);

	int updatItemFl(HisServiceItemFl entiyjt);

}
