package com.enjoyhis.persistence.his.po;

import java.util.Date;

import com.enjoyhis.persistence.base.DBRecord;

public class HisServiceItemFl extends DBRecord {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String itemCode;

	private String itemName;

	private String parentId;

	private Integer hiscode;

	private String pym;

	private Date createTime;

	private Date modifyTime;

	private Integer status;

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getHiscode() {
		return hiscode;
	}

	public void setHiscode(Integer hiscode) {
		this.hiscode = hiscode;
	}

	public String getPym() {
		return pym;
	}

	public void setPym(String pym) {
		this.pym = pym;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}