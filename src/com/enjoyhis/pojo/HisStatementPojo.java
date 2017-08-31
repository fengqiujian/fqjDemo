package com.enjoyhis.pojo;

import java.math.BigDecimal;

import com.enjoyhis.persistence.his.po.HisStatement;

public class HisStatementPojo extends HisStatement {
	private static final long serialVersionUID = 1L;
	private String docName;
	private String accountTypeName;
	private String statusName;
	private BigDecimal discountAmount;

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getAccountTypeName() {
		return accountTypeName;
	}

	public void setAccountTypeName(String accountTypeName) {
		this.accountTypeName = accountTypeName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
}