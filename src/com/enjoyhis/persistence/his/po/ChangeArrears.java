package com.enjoyhis.persistence.his.po;

import java.math.BigDecimal;
import java.util.Date;

import com.enjoyhis.persistence.base.DBRecord;

public class ChangeArrears extends DBRecord {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Long repayStatementId;

	private Date time;

	private Long statementItemid;

	private Long statementId;

	private String unitName;

	private String docName;
	//处置金额
	private BigDecimal totalAmount;
	//优惠金额
	private BigDecimal preAmount;
	//实收金额
	private BigDecimal payAmount;
	//欠费金额
	private BigDecimal arrAmount;
	//已还金额
	private BigDecimal alsoAmount;
	//剩余金额
	private BigDecimal resAmount;
	
	private Long regId;
	
	private String date;
	
	public Long getRegId() {
		return regId;
	}
	public void setRegId(Long regId) {
		this.regId = regId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRepayStatementId() {
		return repayStatementId;
	}
	public void setRepayStatementId(Long repayStatementId) {
		this.repayStatementId = repayStatementId;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Long getStatementItemid() {
		return statementItemid;
	}
	public void setStatementItemid(Long statementItemid) {
		this.statementItemid = statementItemid;
	}
	public Long getStatementId() {
		return statementId;
	}
	public void setStatementId(Long statementId) {
		this.statementId = statementId;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public BigDecimal getPreAmount() {
		return preAmount;
	}
	public void setPreAmount(BigDecimal preAmount) {
		this.preAmount = preAmount;
	}
	public BigDecimal getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
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