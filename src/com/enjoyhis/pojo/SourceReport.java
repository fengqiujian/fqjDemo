package com.enjoyhis.pojo;

import java.math.BigDecimal;

public class SourceReport {
	private String ghTime;
	private String source;
	private String patName;
	private String isnew;
	private String docName;
	private BigDecimal totalAmount;
	private BigDecimal qkAmount;
	private BigDecimal payAmount;
	private BigDecimal debtAmount;

	public String getGhTime() {
		return ghTime;
	}

	public void setGhTime(String ghTime) {
		this.ghTime = ghTime;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getPatName() {
		return patName;
	}

	public void setPatName(String patName) {
		this.patName = patName;
	}

	public String getIsnew() {
		return isnew;
	}

	public void setIsnew(String isnew) {
		this.isnew = isnew;
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

	public BigDecimal getQkAmount() {
		return qkAmount;
	}

	public void setQkAmount(BigDecimal qkAmount) {
		this.qkAmount = qkAmount;
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

}