package com.enjoyhis.persistence.mssql.po;

import com.enjoyhis.persistence.base.DBRecord;

public class Sfsftype extends DBRecord {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String code;

	private String name;

	private String pym;

	private String wbm;

	private String val;

	private Long kjkm;

	private Long isuse;

	private Long usenum;

	private String remark;

	private String parentid;

	private String fullpath;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPym() {
		return pym;
	}

	public void setPym(String pym) {
		this.pym = pym;
	}

	public String getWbm() {
		return wbm;
	}

	public void setWbm(String wbm) {
		this.wbm = wbm;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public Long getKjkm() {
		return kjkm;
	}

	public void setKjkm(Long kjkm) {
		this.kjkm = kjkm;
	}

	public Long getIsuse() {
		return isuse;
	}

	public void setIsuse(Long isuse) {
		this.isuse = isuse;
	}

	public Long getUsenum() {
		return usenum;
	}

	public void setUsenum(Long usenum) {
		this.usenum = usenum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getFullpath() {
		return fullpath;
	}

	public void setFullpath(String fullpath) {
		this.fullpath = fullpath;
	}
}