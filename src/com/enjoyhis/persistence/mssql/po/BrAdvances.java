package com.enjoyhis.persistence.mssql.po;

import java.math.BigDecimal;
import java.util.Date;

import com.enjoyhis.persistence.base.DBRecord;

public class BrAdvances extends DBRecord {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long patId;

	private BigDecimal balance;

	private String paypsd;

	private Date duptdate;

	private String checksum;

	private BigDecimal grants;

	private String ckgrants;

	private Date crtdate;

	private BigDecimal crtmoney;

	public Long getPatId() {
		return patId;
	}

	public void setPatId(Long patId) {
		this.patId = patId;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getPaypsd() {
		return paypsd;
	}

	public void setPaypsd(String paypsd) {
		this.paypsd = paypsd;
	}

	public Date getDuptdate() {
		return duptdate;
	}

	public void setDuptdate(Date duptdate) {
		this.duptdate = duptdate;
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public BigDecimal getGrants() {
		return grants;
	}

	public void setGrants(BigDecimal grants) {
		this.grants = grants;
	}

	public String getCkgrants() {
		return ckgrants;
	}

	public void setCkgrants(String ckgrants) {
		this.ckgrants = ckgrants;
	}

	public Date getCrtdate() {
		return crtdate;
	}

	public void setCrtdate(Date crtdate) {
		this.crtdate = crtdate;
	}

	public BigDecimal getCrtmoney() {
		return crtmoney;
	}

	public void setCrtmoney(BigDecimal crtmoney) {
		this.crtmoney = crtmoney;
	}
}