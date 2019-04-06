package com.enjoyhis.persistence.his.po;

import com.enjoyhis.persistence.base.DBRecord;

public class HisXtXray extends DBRecord {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private String exefile;

	private String inifile;

	private String dbfile;

	private Integer isuse;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExefile() {
		return exefile;
	}

	public void setExefile(String exefile) {
		this.exefile = exefile;
	}

	public String getInifile() {
		return inifile;
	}

	public void setInifile(String inifile) {
		this.inifile = inifile;
	}

	public String getDbfile() {
		return dbfile;
	}

	public void setDbfile(String dbfile) {
		this.dbfile = dbfile;
	}

	public Integer getIsuse() {
		return isuse;
	}

	public void setIsuse(Integer isuse) {
		this.isuse = isuse;
	}
}