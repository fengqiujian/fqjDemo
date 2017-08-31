package com.enjoyhis.persistence.mssql.po;

import java.math.BigDecimal;
import java.util.Date;

import com.enjoyhis.persistence.base.DBRecord;

public class Brgh extends DBRecord {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long ghId;

	private String ghType;

	private String ghNo;

	private Long patId;

	private Long hospId;

	private Long departId;

	private Long drId;

	private Long nurseId;

	private String doctors;

	private Long yyId;

	private Long ysId;

	private Long ghHb;

	private Date ghdate;

	private Date ghtime;

	private BigDecimal ghAmount;

	private BigDecimal ysamount;

	private Long sharetype;

	private String ill;

	private String isnew;

	private String ischuzhen;

	private Long isyy;

	private String iszhuanru;

	private Long iszhuanchu;

	private Long isstoped;

	private Date calltime;

	private Date ruzhentime;

	private Date finishtime;

	private Date shoufeitime;

	private Date zhiliaotime;

	private Long status;

	private Long islocked;

	private Long cancelman;

	private Date canceltime;

	private Long operator;

	private Date opertime;

	private Date modifytime;

	private BigDecimal drDiscount;

	private String regguid;

	private String xray;

	private String ghDetail;

	private Integer othert;

	private Integer okstatus;

	public Long getGhId() {
		return ghId;
	}

	public void setGhId(Long ghId) {
		this.ghId = ghId;
	}

	public String getGhType() {
		return ghType;
	}

	public void setGhType(String ghType) {
		this.ghType = ghType;
	}

	public String getGhNo() {
		return ghNo;
	}

	public void setGhNo(String ghNo) {
		this.ghNo = ghNo;
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

	public Long getDepartId() {
		return departId;
	}

	public void setDepartId(Long departId) {
		this.departId = departId;
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

	public String getDoctors() {
		return doctors;
	}

	public void setDoctors(String doctors) {
		this.doctors = doctors;
	}

	public Long getYyId() {
		return yyId;
	}

	public void setYyId(Long yyId) {
		this.yyId = yyId;
	}

	public Long getYsId() {
		return ysId;
	}

	public void setYsId(Long ysId) {
		this.ysId = ysId;
	}

	public Long getGhHb() {
		return ghHb;
	}

	public void setGhHb(Long ghHb) {
		this.ghHb = ghHb;
	}

	public Date getGhdate() {
		return ghdate;
	}

	public void setGhdate(Date ghdate) {
		this.ghdate = ghdate;
	}

	public Date getGhtime() {
		return ghtime;
	}

	public void setGhtime(Date ghtime) {
		this.ghtime = ghtime;
	}

	public BigDecimal getGhAmount() {
		return ghAmount;
	}

	public void setGhAmount(BigDecimal ghAmount) {
		this.ghAmount = ghAmount;
	}

	public BigDecimal getYsamount() {
		return ysamount;
	}

	public void setYsamount(BigDecimal ysamount) {
		this.ysamount = ysamount;
	}

	public Long getSharetype() {
		return sharetype;
	}

	public void setSharetype(Long sharetype) {
		this.sharetype = sharetype;
	}

	public String getIll() {
		return ill;
	}

	public void setIll(String ill) {
		this.ill = ill;
	}

	public String getIsnew() {
		return isnew;
	}

	public void setIsnew(String isnew) {
		this.isnew = isnew;
	}

	public String getIschuzhen() {
		return ischuzhen;
	}

	public void setIschuzhen(String ischuzhen) {
		this.ischuzhen = ischuzhen;
	}

	public Long getIsyy() {
		return isyy;
	}

	public void setIsyy(Long isyy) {
		this.isyy = isyy;
	}

	public String getIszhuanru() {
		return iszhuanru;
	}

	public void setIszhuanru(String iszhuanru) {
		this.iszhuanru = iszhuanru;
	}

	public Long getIszhuanchu() {
		return iszhuanchu;
	}

	public void setIszhuanchu(Long iszhuanchu) {
		this.iszhuanchu = iszhuanchu;
	}

	public Long getIsstoped() {
		return isstoped;
	}

	public void setIsstoped(Long isstoped) {
		this.isstoped = isstoped;
	}

	public Date getCalltime() {
		return calltime;
	}

	public void setCalltime(Date calltime) {
		this.calltime = calltime;
	}

	public Date getRuzhentime() {
		return ruzhentime;
	}

	public void setRuzhentime(Date ruzhentime) {
		this.ruzhentime = ruzhentime;
	}

	public Date getFinishtime() {
		return finishtime;
	}

	public void setFinishtime(Date finishtime) {
		this.finishtime = finishtime;
	}

	public Date getShoufeitime() {
		return shoufeitime;
	}

	public void setShoufeitime(Date shoufeitime) {
		this.shoufeitime = shoufeitime;
	}

	public Date getZhiliaotime() {
		return zhiliaotime;
	}

	public void setZhiliaotime(Date zhiliaotime) {
		this.zhiliaotime = zhiliaotime;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getIslocked() {
		return islocked;
	}

	public void setIslocked(Long islocked) {
		this.islocked = islocked;
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

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	public BigDecimal getDrDiscount() {
		return drDiscount;
	}

	public void setDrDiscount(BigDecimal drDiscount) {
		this.drDiscount = drDiscount;
	}

	public String getRegguid() {
		return regguid;
	}

	public void setRegguid(String regguid) {
		this.regguid = regguid;
	}

	public String getXray() {
		return xray;
	}

	public void setXray(String xray) {
		this.xray = xray;
	}

	public String getGhDetail() {
		return ghDetail;
	}

	public void setGhDetail(String ghDetail) {
		this.ghDetail = ghDetail;
	}

	public Integer getOthert() {
		return othert;
	}

	public void setOthert(Integer othert) {
		this.othert = othert;
	}

	public Integer getOkstatus() {
		return okstatus;
	}

	public void setOkstatus(Integer okstatus) {
		this.okstatus = okstatus;
	}
}