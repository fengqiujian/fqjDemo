package com.enjoyhis.persistence.his.po;

import com.enjoyhis.persistence.base.DBRecord;

public class AdminRole extends DBRecord {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String roleName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}