package com.enjoyhis.rmiclient;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisServiceItemFl;
import com.enjoyhis.persistence.his.po.HisServiceItemXm;

public interface IHisServiceItemFlXm {

	List<HisServiceItemXm> findAllJt(HisServiceItemXm hisServiceItemXm);

	List<HisServiceItemXm> page4ListXms(HisServiceItemXm hisServiceItemXm, String iNames, String charges, Integer pageNumber, Integer pageSize);

	Integer findCountXms(HisServiceItemXm hisServiceItemXm);

	HisServiceItemFl findByIdToName(String parentId);

	HisServiceItemXm findByIdXmInfo(String itemcode);

	int updatItemXmPrice(HisServiceItemXm hisServiceItemXm);

}
