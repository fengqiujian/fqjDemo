package com.enjoyhis.persistence.his.po;

import java.util.Date;

import com.enjoyhis.persistence.base.DBRecord;

public class HisPatientExt extends DBRecord {

	private static final long serialVersionUID = 1L;

	private Long patId;

	private Date createTime;

	private Date modifyTime;

	public Long getPatId() {
		return patId;
	}

	public void setPatId(Long patId) {
		this.patId = patId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
}