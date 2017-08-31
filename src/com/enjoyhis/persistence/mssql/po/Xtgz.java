package com.enjoyhis.persistence.mssql.po;

import com.enjoyhis.persistence.base.DBRecord;

public class Xtgz extends DBRecord {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer sign;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSign() {
		return sign;
	}

	public void setSign(Integer sign) {
		this.sign = sign;
	}
}