package com.enjoyhis.persistence.mssql.po;

import java.math.BigDecimal;
import java.util.Date;

import com.enjoyhis.persistence.base.DBRecord;

public class Brss extends DBRecord {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long ssId;

	private String ssType;

	private String ssNo;

	private Date ssDate;

	private Long patId;

	private Long hospId;

	private Long discounttype;

	private String discountno;

	private BigDecimal amount;

	private BigDecimal amountown;

	private BigDecimal amountys;

	private BigDecimal amountvip;

	private String actionno;

	private BigDecimal amountaction;

	private String othertype;

	private BigDecimal amountother;

	private String otherno;

	private String discountreason;

	private BigDecimal amountdiscount;

	private BigDecimal amountyf;

	private BigDecimal amountcash;

	private BigDecimal amountchange;

	private BigDecimal amountreturn;

	private String returnreason;

	private Long returnman;

	private Long returnconfirm;

	private Date returndate;

	private Long status;

	private Long printnum;

	private String remark;

	private Long operator;

	private Date opertime;

	private Long cancelman;

	private Date canceltime;

	private Long rowflag;

	private BigDecimal moneyOth1;

	private BigDecimal moneyOth2;

	private BigDecimal arrearage;

	private BigDecimal payoff;

	private Long isfundment;

	private Long ghId;

	private Long sssId;

	private BigDecimal moneyPre;

	private BigDecimal moneyGra;

	private BigDecimal moneyWx;

	private String rowguid;

	private BigDecimal moneyIc;

	private String rowguid2;

	private String outTradeNo;

	private String outRefundNo;

	private String outnos;

	private BigDecimal dmoney;

	private String dnote;

	private String moneyOth2type;

	private String moneyOth2no;

	private String patguid;

	private String regguid;

	private BigDecimal tgpoundage;

	private BigDecimal pospoundage;

	private BigDecimal moneyInsur;

	private String moneyDzyhq;

	private BigDecimal amountss;

	public Long getSsId() {
		return ssId;
	}

	public void setSsId(Long ssId) {
		this.ssId = ssId;
	}

	public String getSsType() {
		return ssType;
	}

	public void setSsType(String ssType) {
		this.ssType = ssType;
	}

	public String getSsNo() {
		return ssNo;
	}

	public void setSsNo(String ssNo) {
		this.ssNo = ssNo;
	}

	public Date getSsDate() {
		return ssDate;
	}

	public void setSsDate(Date ssDate) {
		this.ssDate = ssDate;
	}

	public Long getPatId() {
		return patId;
	}

	public void setPatId(Long patId) {
		this.patId = patId;
	}

	public Long getHospId() {
		return hospId;
	}

	public void setHospId(Long hospId) {
		this.hospId = hospId;
	}

	public Long getDiscounttype() {
		return discounttype;
	}

	public void setDiscounttype(Long discounttype) {
		this.discounttype = discounttype;
	}

	public String getDiscountno() {
		return discountno;
	}

	public void setDiscountno(String discountno) {
		this.discountno = discountno;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmountown() {
		return amountown;
	}

	public void setAmountown(BigDecimal amountown) {
		this.amountown = amountown;
	}

	public BigDecimal getAmountys() {
		return amountys;
	}

	public void setAmountys(BigDecimal amountys) {
		this.amountys = amountys;
	}

	public BigDecimal getAmountvip() {
		return amountvip;
	}

	public void setAmountvip(BigDecimal amountvip) {
		this.amountvip = amountvip;
	}

	public String getActionno() {
		return actionno;
	}

	public void setActionno(String actionno) {
		this.actionno = actionno;
	}

	public BigDecimal getAmountaction() {
		return amountaction;
	}

	public void setAmountaction(BigDecimal amountaction) {
		this.amountaction = amountaction;
	}

	public String getOthertype() {
		return othertype;
	}

	public void setOthertype(String othertype) {
		this.othertype = othertype;
	}

	public BigDecimal getAmountother() {
		return amountother;
	}

	public void setAmountother(BigDecimal amountother) {
		this.amountother = amountother;
	}

	public String getOtherno() {
		return otherno;
	}

	public void setOtherno(String otherno) {
		this.otherno = otherno;
	}

	public String getDiscountreason() {
		return discountreason;
	}

	public void setDiscountreason(String discountreason) {
		this.discountreason = discountreason;
	}

	public BigDecimal getAmountdiscount() {
		return amountdiscount;
	}

	public void setAmountdiscount(BigDecimal amountdiscount) {
		this.amountdiscount = amountdiscount;
	}

	public BigDecimal getAmountyf() {
		return amountyf;
	}

	public void setAmountyf(BigDecimal amountyf) {
		this.amountyf = amountyf;
	}

	public BigDecimal getAmountcash() {
		return amountcash;
	}

	public void setAmountcash(BigDecimal amountcash) {
		this.amountcash = amountcash;
	}

	public BigDecimal getAmountchange() {
		return amountchange;
	}

	public void setAmountchange(BigDecimal amountchange) {
		this.amountchange = amountchange;
	}

	public BigDecimal getAmountreturn() {
		return amountreturn;
	}

	public void setAmountreturn(BigDecimal amountreturn) {
		this.amountreturn = amountreturn;
	}

	public String getReturnreason() {
		return returnreason;
	}

	public void setReturnreason(String returnreason) {
		this.returnreason = returnreason;
	}

	public Long getReturnman() {
		return returnman;
	}

	public void setReturnman(Long returnman) {
		this.returnman = returnman;
	}

	public Long getReturnconfirm() {
		return returnconfirm;
	}

	public void setReturnconfirm(Long returnconfirm) {
		this.returnconfirm = returnconfirm;
	}

	public Date getReturndate() {
		return returndate;
	}

	public void setReturndate(Date returndate) {
		this.returndate = returndate;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getPrintnum() {
		return printnum;
	}

	public void setPrintnum(Long printnum) {
		this.printnum = printnum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getOperator() {
		return operator;
	}

	public void setOperator(Long operator) {
		this.operator = operator;
	}

	public Date getOpertime() {
		return opertime;
	}

	public void setOpertime(Date opertime) {
		this.opertime = opertime;
	}

	public Long getCancelman() {
		return cancelman;
	}

	public void setCancelman(Long cancelman) {
		this.cancelman = cancelman;
	}

	public Date getCanceltime() {
		return canceltime;
	}

	public void setCanceltime(Date canceltime) {
		this.canceltime = canceltime;
	}

	public Long getRowflag() {
		return rowflag;
	}

	public void setRowflag(Long rowflag) {
		this.rowflag = rowflag;
	}

	public BigDecimal getMoneyOth1() {
		return moneyOth1;
	}

	public void setMoneyOth1(BigDecimal moneyOth1) {
		this.moneyOth1 = moneyOth1;
	}

	public BigDecimal getMoneyOth2() {
		return moneyOth2;
	}

	public void setMoneyOth2(BigDecimal moneyOth2) {
		this.moneyOth2 = moneyOth2;
	}

	public BigDecimal getArrearage() {
		return arrearage;
	}

	public void setArrearage(BigDecimal arrearage) {
		this.arrearage = arrearage;
	}

	public BigDecimal getPayoff() {
		return payoff;
	}

	public void setPayoff(BigDecimal payoff) {
		this.payoff = payoff;
	}

	public Long getIsfundment() {
		return isfundment;
	}

	public void setIsfundment(Long isfundment) {
		this.isfundment = isfundment;
	}

	public Long getGhId() {
		return ghId;
	}

	public void setGhId(Long ghId) {
		this.ghId = ghId;
	}

	public Long getSssId() {
		return sssId;
	}

	public void setSssId(Long sssId) {
		this.sssId = sssId;
	}

	public BigDecimal getMoneyPre() {
		return moneyPre;
	}

	public void setMoneyPre(BigDecimal moneyPre) {
		this.moneyPre = moneyPre;
	}

	public BigDecimal getMoneyGra() {
		return moneyGra;
	}

	public void setMoneyGra(BigDecimal moneyGra) {
		this.moneyGra = moneyGra;
	}

	public BigDecimal getMoneyWx() {
		return moneyWx;
	}

	public void setMoneyWx(BigDecimal moneyWx) {
		this.moneyWx = moneyWx;
	}

	public String getRowguid() {
		return rowguid;
	}

	public void setRowguid(String rowguid) {
		this.rowguid = rowguid;
	}

	public BigDecimal getMoneyIc() {
		return moneyIc;
	}

	public void setMoneyIc(BigDecimal moneyIc) {
		this.moneyIc = moneyIc;
	}

	public String getRowguid2() {
		return rowguid2;
	}

	public void setRowguid2(String rowguid2) {
		this.rowguid2 = rowguid2;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getOutRefundNo() {
		return outRefundNo;
	}

	public void setOutRefundNo(String outRefundNo) {
		this.outRefundNo = outRefundNo;
	}

	public String getOutnos() {
		return outnos;
	}

	public void setOutnos(String outnos) {
		this.outnos = outnos;
	}

	public BigDecimal getDmoney() {
		return dmoney;
	}

	public void setDmoney(BigDecimal dmoney) {
		this.dmoney = dmoney;
	}

	public String getDnote() {
		return dnote;
	}

	public void setDnote(String dnote) {
		this.dnote = dnote;
	}

	public String getMoneyOth2type() {
		return moneyOth2type;
	}

	public void setMoneyOth2type(String moneyOth2type) {
		this.moneyOth2type = moneyOth2type;
	}

	public String getMoneyOth2no() {
		return moneyOth2no;
	}

	public void setMoneyOth2no(String moneyOth2no) {
		this.moneyOth2no = moneyOth2no;
	}

	public String getPatguid() {
		return patguid;
	}

	public void setPatguid(String patguid) {
		this.patguid = patguid;
	}

	public String getRegguid() {
		return regguid;
	}

	public void setRegguid(String regguid) {
		this.regguid = regguid;
	}

	public BigDecimal getTgpoundage() {
		return tgpoundage;
	}

	public void setTgpoundage(BigDecimal tgpoundage) {
		this.tgpoundage = tgpoundage;
	}

	public BigDecimal getPospoundage() {
		return pospoundage;
	}

	public void setPospoundage(BigDecimal pospoundage) {
		this.pospoundage = pospoundage;
	}

	public BigDecimal getMoneyInsur() {
		return moneyInsur;
	}

	public void setMoneyInsur(BigDecimal moneyInsur) {
		this.moneyInsur = moneyInsur;
	}

	public String getMoneyDzyhq() {
		return moneyDzyhq;
	}

	public void setMoneyDzyhq(String moneyDzyhq) {
		this.moneyDzyhq = moneyDzyhq;
	}

	public BigDecimal getAmountss() {
		return amountss;
	}

	public void setAmountss(BigDecimal amountss) {
		this.amountss = amountss;
	}
}