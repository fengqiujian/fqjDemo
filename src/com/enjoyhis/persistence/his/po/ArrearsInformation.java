package com.enjoyhis.persistence.his.po;

import java.math.BigDecimal;

import com.enjoyhis.persistence.base.DBRecord;

public class ArrearsInformation extends DBRecord {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//欠费金额
	private BigDecimal arrAmount;
	//已还金额
	private BigDecimal alsoAmount;
	//剩余金额
	private BigDecimal resAmount;
	
	public BigDecimal getArrAmount() {
		return arrAmount;
	}
	public void setArrAmount(BigDecimal arrAmount) {
		this.arrAmount = arrAmount;
	}
	public BigDecimal getAlsoAmount() {
		return alsoAmount;
	}
	public void setAlsoAmount(BigDecimal alsoAmount) {
		this.alsoAmount = alsoAmount;
	}
	public BigDecimal getResAmount() {
		return resAmount;
	}
	public void setResAmount(BigDecimal resAmount) {
		this.resAmount = resAmount;
	}
	
}