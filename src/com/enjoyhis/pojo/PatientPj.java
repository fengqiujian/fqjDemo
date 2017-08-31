package com.enjoyhis.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PatientPj implements Serializable {

	private static final long serialVersionUID = 1L;

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

	private String maindocName;

	private BigDecimal accountAmount;

	private BigDecimal originalAmount;

	private BigDecimal givenAmount;

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

	public String getMaindocName() {
		return maindocName;
	}

	public void setMaindocName(String maindocName) {
		this.maindocName = maindocName;
	}

	public BigDecimal getAccountAmount() {
		return accountAmount;
	}

	public void setAccountAmount(BigDecimal accountAmount) {
		this.accountAmount = accountAmount;
	}

	public BigDecimal getOriginalAmount() {
		return originalAmount;
	}

	public void setOriginalAmount(BigDecimal originalAmount) {
		this.originalAmount = originalAmount;
	}

	public BigDecimal getGivenAmount() {
		return givenAmount;
	}

	public void setGivenAmount(BigDecimal givenAmount) {
		this.givenAmount = givenAmount;
	}

}
