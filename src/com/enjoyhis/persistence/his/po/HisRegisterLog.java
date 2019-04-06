package com.enjoyhis.persistence.his.po;

import java.util.Date;

import com.enjoyhis.persistence.base.DBRecord;

public class HisRegisterLog extends DBRecord {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long regId;

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

	private Long statementId;

	private String outCall;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRegId() {
		return regId;
	}

	public void setRegId(Long regId) {
		this.regId = regId;
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

	public Long getStatementId() {
		return statementId;
	}

	public void setStatementId(Long statementId) {
		this.statementId = statementId;
	}

	public String getOutCall() {
		return outCall;
	}

	public void setOutCall(String outCall) {
		this.outCall = outCall;
	}
}