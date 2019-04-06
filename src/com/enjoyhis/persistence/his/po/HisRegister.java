package com.enjoyhis.persistence.his.po;

import java.util.Date;

import com.enjoyhis.persistence.base.DBRecord;

public class HisRegister extends DBRecord {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Long patId;

	private Date ghTime;

	private Date beginTime;

	private Date endTime;

	private Integer appoLen;

	private String remark;

	private Long dentistId;

	private Date createTime;

	private Date modifyTime;

	private Integer unitCode;

	private Integer isnew;

	private String source;

	private Long msgId;

	private Integer deptCode;

	private String serviceItems;

	private Long operator;

	private Integer isAppoint;

	private Integer status;

	private Long lockUser;

	private Long statementItemid;

	private String outCall;

	private String introducer;

	// wujiaxing new
	private String patName;
	private String patType;

	/**
	 * 是否确认，1为确认
	 */
	private int affirm;

	public HisRegister() {
		super();
	}

	public HisRegister(Long id, Long patId, Date ghTime, Date beginTime, Date endTime, Integer appoLen, String remark, Long dentistId, Date createTime, Date modifyTime, Integer unitCode, Integer isnew, String source, Long msgId, Integer deptCode, String serviceItems, Long operator, Integer isAppoint, Integer status, Long lockUser, Long statementItemid, String outCall) {
		super();
		this.id = id;
		this.patId = patId;
		this.ghTime = ghTime;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.appoLen = appoLen;
		this.remark = remark;
		this.dentistId = dentistId;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.unitCode = unitCode;
		this.isnew = isnew;
		this.source = source;
		this.msgId = msgId;
		this.deptCode = deptCode;
		this.serviceItems = serviceItems;
		this.operator = operator;
		this.isAppoint = isAppoint;
		this.status = status;
		this.lockUser = lockUser;
		this.statementItemid = statementItemid;
		this.outCall = outCall;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPatId() {
		return patId;
	}

	public void setPatId(Long patId) {
		this.patId = patId;
	}

	public Date getGhTime() {
		return ghTime;
	}

	public void setGhTime(Date ghTime) {
		this.ghTime = ghTime;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getAppoLen() {
		return appoLen;
	}

	public void setAppoLen(Integer appoLen) {
		this.appoLen = appoLen;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getDentistId() {
		return dentistId;
	}

	public void setDentistId(Long dentistId) {
		this.dentistId = dentistId;
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

	public Integer getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(Integer unitCode) {
		this.unitCode = unitCode;
	}

	public Integer getIsnew() {
		return isnew;
	}

	public void setIsnew(Integer isnew) {
		this.isnew = isnew;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	public Integer getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(Integer deptCode) {
		this.deptCode = deptCode;
	}

	public String getServiceItems() {
		return serviceItems;
	}

	public void setServiceItems(String serviceItems) {
		this.serviceItems = serviceItems;
	}

	public Long getOperator() {
		return operator;
	}

	public void setOperator(Long operator) {
		this.operator = operator;
	}

	public Integer getIsAppoint() {
		return isAppoint;
	}

	public void setIsAppoint(Integer isAppoint) {
		this.isAppoint = isAppoint;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getLockUser() {
		return lockUser;
	}

	public void setLockUser(Long lockUser) {
		this.lockUser = lockUser;
	}

	public Long getStatementItemid() {
		return statementItemid;
	}

	public void setStatementItemid(Long statementItemid) {
		this.statementItemid = statementItemid;
	}

	public String getOutCall() {
		return outCall;
	}

	public void setOutCall(String outCall) {
		this.outCall = outCall;
	}

	public String getPatName() {
		return patName;
	}

	public void setPatName(String patName) {
		this.patName = patName;
	}

	public int getAffirm() {
		return affirm;
	}

	public void setAffirm(int affirm) {
		this.affirm = affirm;
	}

	public String getPatType() {
		return patType;
	}

	public void setPatType(String patType) {
		this.patType = patType;
	}

	public String getIntroducer() {
		return introducer;
	}

	public void setIntroducer(String introducer) {
		this.introducer = introducer;
	}

}