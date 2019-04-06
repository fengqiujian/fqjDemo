package com.enjoyhis.persistence.his.po;

import com.enjoyhis.persistence.base.DBRecord;

public class HisPosDetails extends DBRecord {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String posName;

	private String bankName;

	private String bankNo;

	private Integer unitCode;

	private Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName == null ? null : posName.trim();
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName == null ? null : bankName.trim();
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo == null ? null : bankNo.trim();
	}

	public Integer getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(Integer unitCode) {
		this.unitCode = unitCode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}