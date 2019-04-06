package com.enjoyhis.persistence.his.po;

import com.enjoyhis.persistence.base.DBRecord;

public class SysConfig extends DBRecord {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String keystr;

	private String valuestr;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKeystr() {
		return keystr;
	}

	public void setKeystr(String keystr) {
		this.keystr = keystr;
	}

	public String getValuestr() {
		return valuestr;
	}

	public void setValuestr(String valuestr) {
		this.valuestr = valuestr;
	}
}