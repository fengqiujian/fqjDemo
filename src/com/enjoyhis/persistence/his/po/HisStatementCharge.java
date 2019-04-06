package com.enjoyhis.persistence.his.po;

import java.math.BigDecimal;
import java.util.Date;

import com.enjoyhis.persistence.base.DBRecord;

/**
 * 结算单收费明细
 */
public class HisStatementCharge extends DBRecord {

	private static final long serialVersionUID = 1L;

	/**
	 * 流水id
	 */
	private Long id;

	/**
	 * 单据id
	 */
	private Long statementId;

	/**
	 * 机构代码
	 */
	private Integer unitCode;

	/**
	 * 记账科目
	 */
	private String paymentType;

	/**
	 * 记账二级科目
	 */
	private String paymentSubtype;

	/**
	 * 实际金额
	 */
	private BigDecimal realAmount;

	/**
	 * 卡劵类序列号
	 */
	private String serialNumber;

	/**
	 * 手续费
	 */
	private BigDecimal tgpoundage;

	/**
	 * 净值
	 */
	private BigDecimal netValue;

	/**
	 * 账务类型：1挂号，2处置收费，3收欠费，4结算单调整，9预存，10退预存，11预存转账，12处置单调整，13会计退费
	 */
	private Integer accountType;

	/**
	 * 医生，前台
	 */
	private Long operator;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 修改时间
	 */
	private Date modifyTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStatementId() {
		return statementId;
	}

	public void setStatementId(Long statementId) {
		this.statementId = statementId;
	}

	public Integer getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(Integer unitCode) {
		this.unitCode = unitCode;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPaymentSubtype() {
		return paymentSubtype;
	}

	public void setPaymentSubtype(String paymentSubtype) {
		this.paymentSubtype = paymentSubtype;
	}

	public BigDecimal getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(BigDecimal realAmount) {
		this.realAmount = realAmount;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public BigDecimal getTgpoundage() {
		return tgpoundage;
	}

	public void setTgpoundage(BigDecimal tgpoundage) {
		this.tgpoundage = tgpoundage;
	}

	public BigDecimal getNetValue() {
		return netValue;
	}

	public void setNetValue(BigDecimal netValue) {
		this.netValue = netValue;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public Long getOperator() {
		return operator;
	}

	public void setOperator(Long operator) {
		this.operator = operator;
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