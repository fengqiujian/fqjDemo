package com.enjoyhis.persistence.his.po;

import java.math.BigDecimal;
import java.util.Date;

import com.enjoyhis.persistence.base.DBRecord;

public class HisStatementItemDetail extends DBRecord {

	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 处置单id
	 */
	private Long statementItemid;

	/**
	 * 机构代码
	 */
	private Integer unitCode;

	/**
	 * 处置项id
	 */
	private String itemId;

	/**
	 * 处置项子id
	 */
	private String itemSubId;

	/**
	 * 总金额
	 */
	private BigDecimal itemAmount;

	/**
	 * 单价
	 */
	private BigDecimal price;

	/**
	 * 单位
	 */
	private String unit;

	/**
	 * 数量
	 */
	private Integer qty;

	/**
	 * 医生id
	 */
	private Long docId;

	/**
	 * 患者id
	 */
	private Long patientId;

	/**
	 * 1新患者
	 */
	private Integer isnew;

	/**
	 * 账务类型：1挂号，2处置收费，3收欠费，4结算单调整，9预存，10退预存，11预存转账，12处置单调整，13会计退费
	 */
	private Integer accountType;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 修改时间
	 */
	private Date modifyTime;

	/**
	 * 牙位
	 */
	private String tooth;

	/**
	 * 是否是正式处置项
	 */
	private Integer ifFormal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStatementItemid() {
		return statementItemid;
	}

	public void setStatementItemid(Long statementItemid) {
		this.statementItemid = statementItemid;
	}

	public Integer getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(Integer unitCode) {
		this.unitCode = unitCode;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId == null ? null : itemId.trim();
	}

	public String getItemSubId() {
		return itemSubId;
	}

	public void setItemSubId(String itemSubId) {
		this.itemSubId = itemSubId == null ? null : itemSubId.trim();
	}

	public BigDecimal getItemAmount() {
		return itemAmount;
	}

	public void setItemAmount(BigDecimal itemAmount) {
		this.itemAmount = itemAmount;
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
		this.unit = unit == null ? null : unit.trim();
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Integer getIsnew() {
		return isnew;
	}

	public void setIsnew(Integer isnew) {
		this.isnew = isnew;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
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

	public String getTooth() {
		return tooth;
	}

	public void setTooth(String tooth) {
		this.tooth = tooth == null ? null : tooth.trim();
	}

	public Integer getIfFormal() {
		return ifFormal;
	}

	public void setIfFormal(Integer ifFormal) {
		this.ifFormal = ifFormal;
	}
}