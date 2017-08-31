package com.enjoyhis.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Patient {

	private Long id;

	private String patName;

	private String pym;

	private Integer unitCode;

	private String mobile;

	private String patNo;

	private String userSex;

	private Integer age;

	private Date birthday;

	private Long maindocId;

	private BigDecimal accountAmount;

	private BigDecimal debtAmount;

	private String remark;

	private String source;

	private String address;

	private String newlyAsk;

	private String allergicHis;

	private Date newlyDate;

	private Integer visitTimes;

	private Date lastappointDate;

	private Date lasthospDate;

	private BigDecimal totalSpand;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPatName() {
		return patName;
	}

	public void setPatName(String patName) {
		this.patName = patName;
	}

	public String getPym() {
		return pym;
	}

	public void setPym(String pym) {
		this.pym = pym;
	}

	public Integer getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(Integer unitCode) {
		this.unitCode = unitCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPatNo() {
		return patNo;
	}

	public void setPatNo(String patNo) {
		this.patNo = patNo;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Long getMaindocId() {
		return maindocId;
	}

	public void setMaindocId(Long maindocId) {
		this.maindocId = maindocId;
	}

	public BigDecimal getAccountAmount() {
		return accountAmount;
	}

	public void setAccountAmount(BigDecimal accountAmount) {
		this.accountAmount = accountAmount;
	}

	public BigDecimal getDebtAmount() {
		return debtAmount;
	}

	public void setDebtAmount(BigDecimal debtAmount) {
		this.debtAmount = debtAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNewlyAsk() {
		return newlyAsk;
	}

	public void setNewlyAsk(String newlyAsk) {
		this.newlyAsk = newlyAsk;
	}

	public String getAllergicHis() {
		return allergicHis;
	}

	public void setAllergicHis(String allergicHis) {
		this.allergicHis = allergicHis;
	}

	public Date getNewlyDate() {
		return newlyDate;
	}

	public void setNewlyDate(Date newlyDate) {
		this.newlyDate = newlyDate;
	}

	public Integer getVisitTimes() {
		return visitTimes;
	}

	public void setVisitTimes(Integer visitTimes) {
		this.visitTimes = visitTimes;
	}

	public Date getLastappointDate() {
		return lastappointDate;
	}

	public void setLastappointDate(Date lastappointDate) {
		this.lastappointDate = lastappointDate;
	}

	public Date getLasthospDate() {
		return lasthospDate;
	}

	public void setLasthospDate(Date lasthospDate) {
		this.lasthospDate = lasthospDate;
	}

	public BigDecimal getTotalSpand() {
		return totalSpand;
	}

	public void setTotalSpand(BigDecimal totalSpand) {
		this.totalSpand = totalSpand;
	}

}
