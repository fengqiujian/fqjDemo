package com.enjoyhis.persistence.his.po;

import java.math.BigDecimal;
import java.util.Date;

import com.enjoyhis.persistence.base.DBRecord;

public class HisServiceItemXm extends DBRecord {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String itemCode;

	private String itemName;

	private String pym;

	private String parentId;

	private Integer level;

	private BigDecimal jtPrice;

	private BigDecimal price;

	private String unit;

	private Date createTime;

	private Date modifyTime;

	private Integer status;

	private Integer isShow;

	private String hospCode;

	private String itemJtname;

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

	public String getPym() {
		return pym;
	}

	public void setPym(String pym) {
		this.pym = pym;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public BigDecimal getJtPrice() {
		return jtPrice;
	}

	public void setJtPrice(BigDecimal jtPrice) {
		this.jtPrice = jtPrice;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public String getHospCode() {
		return hospCode;
	}

	public void setHospCode(String hospCode) {
		this.hospCode = hospCode;
	}

	public String getItemJtname() {
		return itemJtname;
	}

	public void setItemJtname(String itemJtname) {
		this.itemJtname = itemJtname;
	}
}