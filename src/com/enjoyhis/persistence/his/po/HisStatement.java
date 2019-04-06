package com.enjoyhis.persistence.his.po;

import java.math.BigDecimal;
import java.util.Date;

import com.enjoyhis.persistence.base.DBRecord;

/**
 * 结算表
 */
public class HisStatement extends DBRecord {

	private static final long serialVersionUID = 1L;

	/**
	 * 流水id
	 */
	private Long id;

	/**
	 * 患者id
	 */
	private Long patId;

	/**
	 * 1挂号，2处置收费，3收欠费，4结算单调整，9预存，10退预存，11预存转账，12处置单调整，13会计退费
	 */
	private Integer accountType;

	/**
	 * 挂号id
	 */
	private Long regId;

	/**
	 * 处置项id
	 */
	private Long statementItemid;

	/**
	 * 父结算单id
	 */
	private Long parentId;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 修改时间
	 */
	private Date modifyTime;

	/**
	 * 院区码
	 */
	private Integer unitCode;

	/**
	 * 总额
	 */
	private BigDecimal totalAmount;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 医生id
	 */
	private Long docId;

	/**
	 * 操作员
	 */
	private Long operator;

	/**
	 * 实收金额
	 */
	private BigDecimal payAmount;

	/**
	 * 欠款金额
	 */
	private BigDecimal debtAmount;

	/**
	 * 还款金额
	 */
	private BigDecimal payoff;

	/**
	 * 打印次数
	 */
	private Integer printNum;

	/**
	 * 0未结，1已结
	 */
	private Integer status;

	/**
	 * 0正常，1退单
	 */
	private Integer flag;

	/**
	 * 
	 */
	private Integer actId;

	/**
	 * 
	 */
	private String oldStatementCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getStatementItemid() {
		return statementItemid;
	}

	public void setStatementItemid(Long statementItemid) {
		this.statementItemid = statementItemid;
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

	public String getOldStatementCode() {
		return oldStatementCode;
	}

	public void setOldStatementCode(String oldStatementCode) {
		this.oldStatementCode = oldStatementCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}