package com.enjoyhis.persistence.mssql.po;

import java.math.BigDecimal;
import java.util.Date;

import com.enjoyhis.persistence.base.DBRecord;

public class Hruser extends DBRecord {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long userId;

	private String userCode;

	private String userName;

	private String userClass;

	private Long departId;

	private Long hospId;

	private String sysQx;

	private Long sysAdmin;

	private String sex;

	private String pym;

	private String wbm;

	private Date birth;

	private String id;

	private String tel;

	private String cmt;

	private String addr;

	private String zip;

	private String email;

	private String officeTel;

	private String doctorNo;

	private String licenseNo;

	private Long nurseId;

	private Long gwId;

	private String zcId;

	private Long ghHb;

	private BigDecimal salary;

	private String room;

	private BigDecimal tichengbase;

	private String bankaccount;

	private String password;

	private String pswquestion;

	private String pwdanswer;

	private String isonline;

	private String onlinestatus;

	private String isbusy;

	private String iscz;

	private String ipaddress;

	private String marriage;

	private String race;

	private String nation;

	private String jiguan;

	private String zzmm;

	private Date rdrqDate;

	private String hzType;

	private String hzAddr;

	private String dregree;

	private String dregreeNo;

	private String contractType;

	private String contractNo;

	private Date contractBegin;

	private Date contractEnd;

	private String zzzNo;

	private Date zzzBegin;

	private Date zzzEnd;

	private String shbxNo;

	private String yibxNo;

	private String yangbxNo;

	private String gjjNo;

	private Date enterDate;

	private Date leaveDate;

	private Date overDate;

	private Date modifytime;

	private String remark;

	private Long operator;

	private Date opertime;

	private Long maxDrDiscount;

	private Integer works;

	private String staffCode;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserClass() {
		return userClass;
	}

	public void setUserClass(String userClass) {
		this.userClass = userClass;
	}

	public Long getDepartId() {
		return departId;
	}

	public void setDepartId(Long departId) {
		this.departId = departId;
	}

	public Long getHospId() {
		return hospId;
	}

	public void setHospId(Long hospId) {
		this.hospId = hospId;
	}

	public String getSysQx() {
		return sysQx;
	}

	public void setSysQx(String sysQx) {
		this.sysQx = sysQx;
	}

	public Long getSysAdmin() {
		return sysAdmin;
	}

	public void setSysAdmin(Long sysAdmin) {
		this.sysAdmin = sysAdmin;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCmt() {
		return cmt;
	}

	public void setCmt(String cmt) {
		this.cmt = cmt;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOfficeTel() {
		return officeTel;
	}

	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}

	public String getDoctorNo() {
		return doctorNo;
	}

	public void setDoctorNo(String doctorNo) {
		this.doctorNo = doctorNo;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public Long getNurseId() {
		return nurseId;
	}

	public void setNurseId(Long nurseId) {
		this.nurseId = nurseId;
	}

	public Long getGwId() {
		return gwId;
	}

	public void setGwId(Long gwId) {
		this.gwId = gwId;
	}

	public String getZcId() {
		return zcId;
	}

	public void setZcId(String zcId) {
		this.zcId = zcId;
	}

	public Long getGhHb() {
		return ghHb;
	}

	public void setGhHb(Long ghHb) {
		this.ghHb = ghHb;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public BigDecimal getTichengbase() {
		return tichengbase;
	}

	public void setTichengbase(BigDecimal tichengbase) {
		this.tichengbase = tichengbase;
	}

	public String getBankaccount() {
		return bankaccount;
	}

	public void setBankaccount(String bankaccount) {
		this.bankaccount = bankaccount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPswquestion() {
		return pswquestion;
	}

	public void setPswquestion(String pswquestion) {
		this.pswquestion = pswquestion;
	}

	public String getPwdanswer() {
		return pwdanswer;
	}

	public void setPwdanswer(String pwdanswer) {
		this.pwdanswer = pwdanswer;
	}

	public String getIsonline() {
		return isonline;
	}

	public void setIsonline(String isonline) {
		this.isonline = isonline;
	}

	public String getOnlinestatus() {
		return onlinestatus;
	}

	public void setOnlinestatus(String onlinestatus) {
		this.onlinestatus = onlinestatus;
	}

	public String getIsbusy() {
		return isbusy;
	}

	public void setIsbusy(String isbusy) {
		this.isbusy = isbusy;
	}

	public String getIscz() {
		return iscz;
	}

	public void setIscz(String iscz) {
		this.iscz = iscz;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getMarriage() {
		return marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getJiguan() {
		return jiguan;
	}

	public void setJiguan(String jiguan) {
		this.jiguan = jiguan;
	}

	public String getZzmm() {
		return zzmm;
	}

	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}

	public Date getRdrqDate() {
		return rdrqDate;
	}

	public void setRdrqDate(Date rdrqDate) {
		this.rdrqDate = rdrqDate;
	}

	public String getHzType() {
		return hzType;
	}

	public void setHzType(String hzType) {
		this.hzType = hzType;
	}

	public String getHzAddr() {
		return hzAddr;
	}

	public void setHzAddr(String hzAddr) {
		this.hzAddr = hzAddr;
	}

	public String getDregree() {
		return dregree;
	}

	public void setDregree(String dregree) {
		this.dregree = dregree;
	}

	public String getDregreeNo() {
		return dregreeNo;
	}

	public void setDregreeNo(String dregreeNo) {
		this.dregreeNo = dregreeNo;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public Date getContractBegin() {
		return contractBegin;
	}

	public void setContractBegin(Date contractBegin) {
		this.contractBegin = contractBegin;
	}

	public Date getContractEnd() {
		return contractEnd;
	}

	public void setContractEnd(Date contractEnd) {
		this.contractEnd = contractEnd;
	}

	public String getZzzNo() {
		return zzzNo;
	}

	public void setZzzNo(String zzzNo) {
		this.zzzNo = zzzNo;
	}

	public Date getZzzBegin() {
		return zzzBegin;
	}

	public void setZzzBegin(Date zzzBegin) {
		this.zzzBegin = zzzBegin;
	}

	public Date getZzzEnd() {
		return zzzEnd;
	}

	public void setZzzEnd(Date zzzEnd) {
		this.zzzEnd = zzzEnd;
	}

	public String getShbxNo() {
		return shbxNo;
	}

	public void setShbxNo(String shbxNo) {
		this.shbxNo = shbxNo;
	}

	public String getYibxNo() {
		return yibxNo;
	}

	public void setYibxNo(String yibxNo) {
		this.yibxNo = yibxNo;
	}

	public String getYangbxNo() {
		return yangbxNo;
	}

	public void setYangbxNo(String yangbxNo) {
		this.yangbxNo = yangbxNo;
	}

	public String getGjjNo() {
		return gjjNo;
	}

	public void setGjjNo(String gjjNo) {
		this.gjjNo = gjjNo;
	}

	public Date getEnterDate() {
		return enterDate;
	}

	public void setEnterDate(Date enterDate) {
		this.enterDate = enterDate;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	public Date getOverDate() {
		return overDate;
	}

	public void setOverDate(Date overDate) {
		this.overDate = overDate;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getOperator() {
		return operator;
	}

	public void setOperator(Long operator) {
		this.operator = operator;
	}

	public Date getOpertime() {
		return opertime;
	}

	public void setOpertime(Date opertime) {
		this.opertime = opertime;
	}

	public Long getMaxDrDiscount() {
		return maxDrDiscount;
	}

	public void setMaxDrDiscount(Long maxDrDiscount) {
		this.maxDrDiscount = maxDrDiscount;
	}

	public Integer getWorks() {
		return works;
	}

	public void setWorks(Integer works) {
		this.works = works;
	}

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}
}