package com.enjoyhis.persistence.his.po;

import java.math.BigDecimal;
import java.util.Date;

import com.enjoyhis.persistence.base.DBRecord;

public class HisStatementDel extends DBRecord {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long sId;

	private Long patId;

	private Integer accountType;

	private Long regId;

	private Long parentId;

	private Date createTime;

	private Date modifyTime;

	private Integer unitCode;

	private BigDecimal totalAmount;

	private String remark;

	private Long docId;

	private Long operator;

	private BigDecimal payAmount;

	private BigDecimal debtAmount;

	private BigDecimal payoff;

	private Integer printNum;

	private Integer status;

	private Integer flag;

	private Integer actId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getsId() {
		return sId;
	}

	public void setsId(Long sId) {
		this.sId = sId;
	}

	public Long getPatId() {
		return patId;
	}

	public void setPatId(Long patId) {
		this.patId = patId;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public Long getRegId() {
		return regId;
	}

	public void setRegId(Long regId) {
		this.regId = regId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
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

	public Integer getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(Integer unitCode) {
		this.unitCode = unitCode;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}

	public Long getOperator() {
		return operator;
	}

	public void setOperator(Long operator) {
		this.operator = operator;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public BigDecimal getDebtAmount() {
		return debtAmount;
	}

	public void setDebtAmount(BigDecimal debtAmount) {
		this.debtAmount = debtAmount;
	}

	public BigDecimal getPayoff() {
		return payoff;
	}

	public void setPayoff(BigDecimal payoff) {
		this.payoff = payoff;
	}

	public Integer getPrintNum() {
		return printNum;
	}

	public void setPrintNum(Integer printNum) {
		this.printNum = printNum;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getActId() {
		return actId;
	}

	public void setActId(Integer actId) {
		this.actId = actId;
	}
}