package com.enjoyhis.persistence.his.po;

/**
 * 医生常选预约目的
 * 
 * @author wujiaxing
 *
 */
public class HisEmployeeBookingitem {
	private Integer id;

	/**
	 * 医生ID
	 */
	private Long employeeId;

	/**
	 * 目的ID
	 */
	private String itemName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName == null ? null : itemName.trim();
	}
}