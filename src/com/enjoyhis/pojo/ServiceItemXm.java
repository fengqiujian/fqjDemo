package com.enjoyhis.pojo;

import com.enjoyhis.persistence.his.po.HisServiceItemXm;

public class ServiceItemXm extends HisServiceItemXm {

	private static final long serialVersionUID = 1L;
	private String itemParentName;
	private String firCode;
	private String firName;

	public String getItemParentName() {
		return itemParentName;
	}

	public void setItemParentName(String itemParentName) {
		this.itemParentName = itemParentName;
	}

	public String getFirCode() {
		return firCode;
	}

	public void setFirCode(String firCode) {
		this.firCode = firCode;
	}

	public String getFirName() {
		return firName;
	}

	public void setFirName(String firName) {
		this.firName = firName;
	}

}
