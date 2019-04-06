package com.enjoyhis.pojo;

import java.io.Serializable;
import java.util.Date;

/** (REPORT_PROCESS) **/
public class Process implements Serializable {

	private static final long serialVersionUID = 1L;

	/** (Not Null) */
	private Long id;
	/** 统计时间 */
	private Date statDate;
	/** 状态，0未扎帐，1锁HIS，2预生成报表，3审计通过，4扎帐结束 */
	private Integer status;
	/** 单位代码 */
	private String unitCode;
	/** 操作人员 */
	private String operator;
	/** 是否正在执行 */
	private Integer isrunning;

	private Integer dateStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStatDate() {
		return statDate;
	}

	public void setStatDate(Date statDate) {
		this.statDate = statDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Integer getIsrunning() {
		return isrunning;
	}

	public void setIsrunning(Integer isrunning) {
		this.isrunning = isrunning;
	}

	public Integer getDateStatus() {
		return dateStatus;
	}

	public void setDateStatus(Integer dateStatus) {
		this.dateStatus = dateStatus;
	}

}