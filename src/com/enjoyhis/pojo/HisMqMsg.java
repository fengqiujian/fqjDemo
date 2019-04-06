package com.enjoyhis.pojo;

import java.math.BigDecimal;

import com.enjoyhis.util.HisMqEnum;

public class HisMqMsg {

	Long id;

	String objname;
	
	BigDecimal payAmount;
	Integer status;

	// 可以理解为key
	HisMqEnum hisMqEnum;

	public HisMqMsg(Long id, String objname, HisMqEnum hisMqEnum) {
		this.id = id;
		this.objname = objname;
		this.hisMqEnum = hisMqEnum;
	}
	public HisMqMsg(Long id, String objname,BigDecimal payAmount,Integer status) {
		this.id = id;
		this.objname = objname;
		this.payAmount = payAmount;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObjname() {
		return objname;
	}

	public void setObjname(String objname) {
		this.objname = objname;
	}

	public HisMqEnum getHisMqEnum() {
		return hisMqEnum;
	}

	public void setHisMqEnum(HisMqEnum hisMqEnum) {
		this.hisMqEnum = hisMqEnum;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
