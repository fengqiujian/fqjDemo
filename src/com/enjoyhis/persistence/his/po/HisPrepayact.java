package com.enjoyhis.persistence.his.po;

import java.math.BigDecimal;
import java.util.Date;

import com.enjoyhis.persistence.base.DBRecord;

public class HisPrepayact extends DBRecord {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String actname;

	private BigDecimal payAmount;

	private BigDecimal givenAmount;

	private Integer status;

	private Date createTime;

	private Date modifyTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActname() {
		return actname;
	}

	public void setActname(String actname) {
		this.actname = actname;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public BigDecimal getGivenAmount() {
		return givenAmount;
	}

	public void setGivenAmount(BigDecimal givenAmount) {
		this.givenAmount = givenAmount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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