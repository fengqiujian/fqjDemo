package com.enjoyhis.persistence.his.po;

import com.enjoyhis.persistence.base.DBRecord;

public class SysSeq extends DBRecord {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private Long currid;

	private Integer increment;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCurrid() {
		return currid;
	}

	public void setCurrid(Long currid) {
		this.currid = currid;
	}

	public Integer getIncrement() {
		return increment;
	}

	public void setIncrement(Integer increment) {
		this.increment = increment;
	}
}