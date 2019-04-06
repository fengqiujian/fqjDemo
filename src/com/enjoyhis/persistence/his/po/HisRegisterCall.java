package com.enjoyhis.persistence.his.po;

import java.util.Date;

import com.enjoyhis.persistence.base.DBRecord;

public class HisRegisterCall extends DBRecord {

	private static final long serialVersionUID = 2664397188609560233L;

	private Integer id;

	private Long ghId;

	private Integer callno;

	private Integer calltype;// 0-无，1-排，2-叫，3-过，4-结，5-废

	private Date callDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getGhId() {
		return ghId;
	}

	public void setGhId(Long ghId) {
		this.ghId = ghId;
	}

	public Integer getCallno() {
		return callno;
	}

	public void setCallno(Integer callno) {
		this.callno = callno;
	}

	public Integer getCalltype() {
		return calltype;
	}

	public void setCalltype(Integer calltype) {
		this.calltype = calltype;
	}

	public Date getCallDate() {
		return callDate;
	}

	public void setCallDate(Date callDate) {
		this.callDate = callDate;
	}
}