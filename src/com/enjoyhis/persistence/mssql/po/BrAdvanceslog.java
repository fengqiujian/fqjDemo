package com.enjoyhis.persistence.mssql.po;

import java.math.BigDecimal;
import java.util.Date;

import com.enjoyhis.persistence.base.DBRecord;

public class BrAdvanceslog extends DBRecord {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String rowguid;

	private Long patId;

	private BigDecimal addmoney;

	private BigDecimal prebala;

	private Date duptdate;

	private Long who;

	private Integer rowflag;

	private Long ssId;

	private String remark;

	private BigDecimal addcash;

	private String othertype;

	private String otherno;

	private BigDecimal addgrants;

	private Long operator;

	private Long hospId;

	private Date opdate;

	private BigDecimal amountother;

	private Long relatepat;

	public String getRowguid() {
		return rowguid;
	}

	public void setRowguid(String rowguid) {
		this.rowguid = rowguid;
	}

	public Long getPatId() {
		return patId;
	}

	public void setPatId(Long patId) {
		this.patId = patId;
	}

	public BigDecimal getAddmoney() {
		return addmoney;
	}

	public void setAddmoney(BigDecimal addmoney) {
		this.addmoney = addmoney;
	}

	public BigDecimal getPrebala() {
		return prebala;
	}

	public void setPrebala(BigDecimal prebala) {
		this.prebala = prebala;
	}

	public Date getDuptdate() {
		return duptdate;
	}

	public void setDuptdate(Date duptdate) {
		this.duptdate = duptdate;
	}

	public Long getWho() {
		return who;
	}

	public void setWho(Long who) {
		this.who = who;
	}

	public Integer getRowflag() {
		return rowflag;
	}

	public void setRowflag(Integer rowflag) {
		this.rowflag = rowflag;
	}

	public Long getSsId() {
		return ssId;
	}

	public void setSsId(Long ssId) {
		this.ssId = ssId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getAddcash() {
		return addcash;
	}

	public void setAddcash(BigDecimal addcash) {
		this.addcash = addcash;
	}

	public String getOthertype() {
		return othertype;
	}

	public void setOthertype(String othertype) {
		this.othertype = othertype;
	}

	public String getOtherno() {
		return otherno;
	}

	public void setOtherno(String otherno) {
		this.otherno = otherno;
	}

	public BigDecimal getAddgrants() {
		return addgrants;
	}

	public void setAddgrants(BigDecimal addgrants) {
		this.addgrants = addgrants;
	}

	public Long getOperator() {
		return operator;
	}

	public void setOperator(Long operator) {
		this.operator = operator;
	}

	public Long getHospId() {
		return hospId;
	}

	public void setHospId(Long hospId) {
		this.hospId = hospId;
	}

	public Date getOpdate() {
		return opdate;
	}

	public void setOpdate(Date opdate) {
		this.opdate = opdate;
	}

	public BigDecimal getAmountother() {
		return amountother;
	}

	public void setAmountother(BigDecimal amountother) {
		this.amountother = amountother;
	}

	public Long getRelatepat() {
		return relatepat;
	}

	public void setRelatepat(Long relatepat) {
		this.relatepat = relatepat;
	}
}