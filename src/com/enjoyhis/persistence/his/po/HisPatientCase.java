package com.enjoyhis.persistence.his.po;

import java.util.Date;

import com.enjoyhis.persistence.base.DBRecord;

public class HisPatientCase extends DBRecord {

	private static final long serialVersionUID = 1L;

	private Long patId;

	private Long id;

	private Date createTime;

	private Date modifyTime;

	private String tooth;

	private String zs;

	private String xbs;

	private String jwbs;

	private String jc;

	private String zd;

	private String cl;

	private String yz;

	private Date blDate;

	private Long did;

	private String jzsx;

	private String remark;

	public Long getPatId() {
		return patId;
	}

	public void setPatId(Long patId) {
		this.patId = patId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		this.tooth = tooth;
	}

	public String getZs() {
		return zs;
	}

	public void setZs(String zs) {
		this.zs = zs;
	}

	public String getXbs() {
		return xbs;
	}

	public void setXbs(String xbs) {
		this.xbs = xbs;
	}

	public String getJwbs() {
		return jwbs;
	}

	public void setJwbs(String jwbs) {
		this.jwbs = jwbs;
	}

	public String getJc() {
		return jc;
	}

	public void setJc(String jc) {
		this.jc = jc;
	}

	public String getZd() {
		return zd;
	}

	public void setZd(String zd) {
		this.zd = zd;
	}

	public String getCl() {
		return cl;
	}

	public void setCl(String cl) {
		this.cl = cl;
	}

	public String getYz() {
		return yz;
	}

	public void setYz(String yz) {
		this.yz = yz;
	}

	public Date getBlDate() {
		return blDate;
	}

	public void setBlDate(Date blDate) {
		this.blDate = blDate;
	}

	public Long getDid() {
		return did;
	}

	public void setDid(Long did) {
		this.did = did;
	}

	public String getJzsx() {
		return jzsx;
	}

	public void setJzsx(String jzsx) {
		this.jzsx = jzsx;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "HisPatientCase [patId=" + patId + ", id=" + id + ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", tooth=" + tooth + ", zs=" + zs + ", xbs=" + xbs + ", jwbs=" + jwbs + ", jc=" + jc + ", zd=" + zd + ", cl=" + cl + ", yz=" + yz + ", blDate=" + blDate + ", did=" + did + ", jzsx=" + jzsx + ", remark=" + remark + "]";
	}

}