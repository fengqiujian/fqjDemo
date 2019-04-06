package com.enjoyhis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.dao.HisServiceItemDao;
import com.enjoyhis.persistence.his.dao.HisServiceItemFlDao;
import com.enjoyhis.persistence.his.dao.HisServiceItemXmDao;
import com.enjoyhis.persistence.his.po.HisServiceItem;
import com.enjoyhis.persistence.his.po.HisServiceItemFl;
import com.enjoyhis.persistence.his.po.HisServiceItemXm;

@Service("disposalItemService")
public class DisposalItemService {
	@Autowired
	HisServiceItemDao hisServiceItemDao;
	@Autowired
	HisServiceItemFlDao hisServiceItemFlDao;
	@Autowired
	HisServiceItemXmDao hisServiceItemXmDao;

	/**
	 * 获取一级处置项
	 * 
	 * @return
	 */
	public List<HisServiceItem> getHisServiceItemList() {
		HisServiceItem hsi = new HisServiceItem();
		hsi.setSqlSort(" item_code asc");
		return hisServiceItemDao.selectList(hsi);
	}

	/**
	 * 获取二级处置项
	 * 
	 * @return
	 */
	public List<HisServiceItemFl> getHisServiceItemFlList(HisServiceItemFl hsif) {
		hsif.setSqlSort(" item_code asc");
		return hisServiceItemFlDao.selectList(hsif);
	}

	/**
	 * 获取三级处置项
	 * 
	 * @return
	 */
	public List<HisServiceItemXm> getHisServiceItemXmList(HisServiceItemXm hsix) {
		hsix.setSqlSort(" item_code asc");
		return hisServiceItemXmDao.selectList(hsix);
	}

}
