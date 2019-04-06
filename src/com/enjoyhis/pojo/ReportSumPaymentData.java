package com.enjoyhis.pojo;

import java.math.BigDecimal;

/**
 * 收款信息po类
 */
public class ReportSumPaymentData {
	/**
	 * 付款类型
	 */
	private String paymentType;
	/**
	 * 付款子类型
	 */
	private String paymentSubtype;
	/**
	 * 付款金额
	 */
	private BigDecimal amount;
	/**
	 * 付款时间
	 */
	private String time;

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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}