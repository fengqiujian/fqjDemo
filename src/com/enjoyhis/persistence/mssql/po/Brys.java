package com.enjoyhis.persistence.mssql.po;

import java.math.BigDecimal;
import java.util.Date;

import com.enjoyhis.persistence.base.DBRecord;

public class Brys extends DBRecord {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long ysId;

	private Date ysDate;

	private String ysType;

	private Long hospId;

	private Long patId;

	private Long ghId;

	private Long zyId;

	private Long drId;

	private Long nurseId;

	private Long sfxmId;

	private BigDecimal price;

	private BigDecimal cost;

	private String unit;

	private BigDecimal discount;

	private BigDecimal qty;

	private BigDecimal qtyT;

	private BigDecimal amount;

	private BigDecimal amountinfact;

	private Long status;

	private Long ssId;

	private Long yjId;

	private String yjNo;

	private Long planId;

	private String fdi;

	private String sidepos;

	private String part;

	private String result;

	private String sehao;

	private String yppinlv;

	private BigDecimal ypjiliang;

	private String ypjiliangunit;

	private String ypyongfa;

	private String ypshiji;

	private Long executor;

	private Date executetime;

	private Long operator;

	private Date opertime;

	private String cancelreason;

	private Long cancelman;

	private Date canceltime;

	private String remark;

	private String treatguid;

	private Boolean ldele;

	private Integer nstate;

	private String regguid;

	private String patNo;

	private String chargeguid;

	private BigDecimal bftpayable;

	private BigDecimal bftclsvCoinAmt;

	private BigDecimal bfthphpAmt;

	public Long getYsId() {
		return ysId;
	}

	public void setYsId(Long ysId) {
		this.ysId = ysId;
	}

	public Date getYsDate() {
		return ysDate;
	}

	public void setYsDate(Date ysDate) {
		this.ysDate = ysDate;
	}

	public String getYsType() {
		return ysType;
	}

	public void setYsType(String ysType) {
		this.ysType = ysType;
	}

	public Long getHospId() {
		return hospId;
	}

	public void setHospId(Long hospId) {
		this.hospId = hospId;
	}

	public Long getPatId() {
		return patId;
	}

	public void setPatId(Long patId) {
		this.patId = patId;
	}

	public Long getGhId() {
		return ghId;
	}

	public void setGhId(Long ghId) {
		this.ghId = ghId;
	}

	public Long getZyId() {
		return zyId;
	}

	public void setZyId(Long zyId) {
		this.zyId = zyId;
	}

	public Long getDrId() {
		return drId;
	}

	public void setDrId(Long drId) {
		this.drId = drId;
	}

	public Long getNurseId() {
		return nurseId;
	}

	public void setNurseId(Long nurseId) {
		this.nurseId = nurseId;
	}

	public Long getSfxmId() {
		return sfxmId;
	}

	public void setSfxmId(Long sfxmId) {
		this.sfxmId = sfxmId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getQtyT() {
		return qtyT;
	}

	public void setQtyT(BigDecimal qtyT) {
		this.qtyT = qtyT;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmountinfact() {
		return amountinfact;
	}

	public void setAmountinfact(BigDecimal amountinfact) {
		this.amountinfact = amountinfact;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getSsId() {
		return ssId;
	}

	public void setSsId(Long ssId) {
		this.ssId = ssId;
	}

	public Long getYjId() {
		return yjId;
	}

	public void setYjId(Long yjId) {
		this.yjId = yjId;
	}

	public String getYjNo() {
		return yjNo;
	}

	public void setYjNo(String yjNo) {
		this.yjNo = yjNo;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public String getFdi() {
		return fdi;
	}

	public void setFdi(String fdi) {
		this.fdi = fdi;
	}

	public String getSidepos() {
		return sidepos;
	}

	public void setSidepos(String sidepos) {
		this.sidepos = sidepos;
	}

	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getSehao() {
		return sehao;
	}

	public void setSehao(String sehao) {
		this.sehao = sehao;
	}

	public String getYppinlv() {
		return yppinlv;
	}

	public void setYppinlv(String yppinlv) {
		this.yppinlv = yppinlv;
	}

	public BigDecimal getYpjiliang() {
		return ypjiliang;
	}

	public void setYpjiliang(BigDecimal ypjiliang) {
		this.ypjiliang = ypjiliang;
	}

	public String getYpjiliangunit() {
		return ypjiliangunit;
	}

	public void setYpjiliangunit(String ypjiliangunit) {
		this.ypjiliangunit = ypjiliangunit;
	}

	public String getYpyongfa() {
		return ypyongfa;
	}

	public void setYpyongfa(String ypyongfa) {
		this.ypyongfa = ypyongfa;
	}

	public String getYpshiji() {
		return ypshiji;
	}

	public void setYpshiji(String ypshiji) {
		this.ypshiji = ypshiji;
	}

	public Long getExecutor() {
		return executor;
	}

	public void setExecutor(Long executor) {
		this.executor = executor;
	}

	public Date getExecutetime() {
		return executetime;
	}

	public void setExecutetime(Date executetime) {
		this.executetime = executetime;
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

	public String getCancelreason() {
		return cancelreason;
	}

	public void setCancelreason(String cancelreason) {
		this.cancelreason = cancelreason;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTreatguid() {
		return treatguid;
	}

	public void setTreatguid(String treatguid) {
		this.treatguid = treatguid;
	}

	public Boolean getLdele() {
		return ldele;
	}

	public void setLdele(Boolean ldele) {
		this.ldele = ldele;
	}

	public Integer getNstate() {
		return nstate;
	}

	public void setNstate(Integer nstate) {
		this.nstate = nstate;
	}

	public String getRegguid() {
		return regguid;
	}

	public void setRegguid(String regguid) {
		this.regguid = regguid;
	}

	public String getPatNo() {
		return patNo;
	}

	public void setPatNo(String patNo) {
		this.patNo = patNo;
	}

	public String getChargeguid() {
		return chargeguid;
	}

	public void setChargeguid(String chargeguid) {
		this.chargeguid = chargeguid;
	}

	public BigDecimal getBftpayable() {
		return bftpayable;
	}

	public void setBftpayable(BigDecimal bftpayable) {
		this.bftpayable = bftpayable;
	}

	public BigDecimal getBftclsvCoinAmt() {
		return bftclsvCoinAmt;
	}

	public void setBftclsvCoinAmt(BigDecimal bftclsvCoinAmt) {
		this.bftclsvCoinAmt = bftclsvCoinAmt;
	}

	public BigDecimal getBfthphpAmt() {
		return bfthphpAmt;
	}

	public void setBfthphpAmt(BigDecimal bfthphpAmt) {
		this.bfthphpAmt = bfthphpAmt;
	}
}