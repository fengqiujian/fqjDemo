package com.enjoyhis.persistence.his.po;

import java.math.BigDecimal;
import java.util.Date;

import com.enjoyhis.persistence.base.DBRecord;

// 患者表
public class HisPatient extends DBRecord {

	private static final long serialVersionUID = 1L;

	/**
	 * 自增+院区编号
	 */
	private Long id;

	/**
	 * 患者名字
	 */
	private String patName;

	/**
	 * 拼音
	 */
	private String pym;

	/**
	 * 院区代码
	 */
	private Integer unitCode;

	/**
	 * 注册时间
	 */
	private Date createTime;

	/**
	 * 修改时间
	 */
	private Date modifyTime;

	/**
	 * 手机号
	 */
	private String mobile;

	/**
	 * 病历编号
	 */
	private String patNo;

	/**
	 * 性别
	 */
	private String userSex;

	/**
	 * 年龄，生日字段参考
	 */
	private Integer age;

	/**
	 * 生日
	 */
	private Date birthday;

	/**
	 * 主治医生id
	 */
	private Long maindocId;

	/**
	 * 主治医生名字
	 */
	private String maindocName;

	/**
	 * 账户余额
	 */
	private BigDecimal accountAmount;

	/**
	 * 充值金额
	 */
	private BigDecimal originalAmount;

	/**
	 * 赠送金额
	 */
	private BigDecimal givenAmount;

	/**
	 * 欠款
	 */
	private BigDecimal debtAmount;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 来源
	 */
	private String source;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 初诊询问
	 */
	private String newlyAsk;

	/**
	 * 过敏史
	 */
	private String allergicHis;

	/**
	 * 初诊日期
	 */
	private Date newlyDate;

	/**
	 * 就诊次数
	 */
	private Integer visitTimes;

	/**
	 * 最近预约
	 */
	private Date lastappointDate;

	/**
	 * 最近就诊
	 */
	private Date lasthospDate;

	/**
	 * 累计消费
	 */
	private BigDecimal totalSpand;

	/**
	 * 介绍人
	 */
	private String introducer;

	/**
	 * 赠金比率
	 */
	private BigDecimal givenCoeff;

	/**
	 * 电话
	 */
	private String tel;

	/**
	 * 电子邮箱
	 */
	private String email;

	/**
	 * 身份证号
	 */
	private String persid;

	/**
	 * 患者类型（1正畸患者，2种植患者，可多选）
	 */
	private String type;

	public HisPatient() {
		super();
	}

	public HisPatient(Long id, String patName, String pym, Integer unitCode, Date createTime, Date modifyTime, String mobile, String patNo, String userSex, Integer age, Date birthday, Long maindocId, String maindocName, BigDecimal accountAmount, BigDecimal originalAmount, BigDecimal givenAmount, BigDecimal debtAmount, String remark, String source, String address, String newlyAsk, String allergicHis, Date newlyDate, Integer visitTimes, Date lastappointDate, Date lasthospDate, BigDecimal totalSpand, String introducer, BigDecimal givenCoeff, String tel, String email, String persid, String type) {
		super();
		this.id = id;
		this.patName = patName;
		this.pym = pym;
		this.unitCode = unitCode;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.mobile = mobile;
		this.patNo = patNo;
		this.userSex = userSex;
		this.age = age;
		this.birthday = birthday;
		this.maindocId = maindocId;
		this.maindocName = maindocName;
		this.accountAmount = accountAmount;
		this.originalAmount = originalAmount;
		this.givenAmount = givenAmount;
		this.debtAmount = debtAmount;
		this.remark = remark;
		this.source = source;
		this.address = address;
		this.newlyAsk = newlyAsk;
		this.allergicHis = allergicHis;
		this.newlyDate = newlyDate;
		this.visitTimes = visitTimes;
		this.lastappointDate = lastappointDate;
		this.lasthospDate = lasthospDate;
		this.totalSpand = totalSpand;
		this.introducer = introducer;
		this.givenCoeff = givenCoeff;
		this.tel = tel;
		this.email = email;
		this.persid = persid;
		this.type = type;
	}

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
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

	public String getIntroducer() {
		return introducer;
	}

	public void setIntroducer(String introducer) {
		this.introducer = introducer;
	}

	public BigDecimal getGivenCoeff() {
		return givenCoeff;
	}

	public void setGivenCoeff(BigDecimal givenCoeff) {
		this.givenCoeff = givenCoeff;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPersid() {
		return persid;
	}

	public void setPersid(String persid) {
		this.persid = persid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "HisPatient [id=" + id + ", patName=" + patName + ", pym=" + pym + ", unitCode=" + unitCode + ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", mobile=" + mobile + ", patNo=" + patNo + ", userSex=" + userSex + ", age=" + age + ", birthday=" + birthday + ", maindocId=" + maindocId + ", maindocName=" + maindocName + ", accountAmount=" + accountAmount + ", originalAmount=" + originalAmount + ", givenAmount=" + givenAmount + ", debtAmount=" + debtAmount + ", remark=" + remark + ", source=" + source + ", address=" + address + ", newlyAsk=" + newlyAsk + ", allergicHis=" + allergicHis + ", newlyDate=" + newlyDate + ", visitTimes=" + visitTimes + ", lastappointDate=" + lastappointDate + ", lasthospDate=" + lasthospDate + ", totalSpand=" + totalSpand + ", introducer=" + introducer + ", givenCoeff=" + givenCoeff + ", tel=" + tel + ", email=" + email + ", persid=" + persid + ", type=" + type + "]";
	}

}