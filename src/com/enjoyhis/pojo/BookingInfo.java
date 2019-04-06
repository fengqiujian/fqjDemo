package com.enjoyhis.pojo;

public class BookingInfo {

	private Long id;
	/**
	 * 预约患者名称
	 */
	private String patientName;
	/**
	 * 预约目的
	 */
	private String serviceItems;

	/**
	 * 预约日期（eg: 2016-10-10）
	 */
	private String date;
	/**
	 * 开始时间（eg: 07:00）
	 */
	private String beginTime;
	/**
	 * 结束时间（eg: 07:00）
	 */
	private String endTime;
	/**
	 * 预约医生ID
	 */
	private Long docId;

	/**
	 * 是否确认
	 */
	private int affirm;

	/**
	 * vip特殊用户
	 */
	private String patientType;

	/**
	 * 预约状态
	 */
	private Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getServiceItems() {
		return serviceItems;
	}

	public void setServiceItems(String serviceItems) {
		this.serviceItems = serviceItems;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}

	public int getAffirm() {
		return affirm;
	}

	public void setAffirm(int affirm) {
		this.affirm = affirm;
	}

	public String getPatientType() {
		return patientType;
	}

	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
