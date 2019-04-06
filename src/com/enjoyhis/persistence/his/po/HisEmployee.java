package com.enjoyhis.persistence.his.po;

import java.math.BigDecimal;
import java.util.Date;

import com.enjoyhis.persistence.base.DBRecord;

public class HisEmployee extends DBRecord {

	private static final long serialVersionUID = 1L;

	/**
	 * 自增+院区编号
	 */
	private Long id;

	/**
	 * 雇员名称
	 */
	private String employeeName;

	/**
	 * 注册时间
	 */
	private Date createTime;

	/**
	 * 机构代码
	 */
	private Integer unitCode;

	/**
	 * 岗位，1医生，2前台
	 */
	private Integer userType;

	/**
	 * 允许登录
	 */
	private Integer islogin;

	/**
	 * 手机号
	 */
	private String mobile;

	/**
	 * 登录密码
	 */
	private String password;

	/**
	 * 上次登录时间
	 */
	private Date lastvistTime;

	/**
	 * 系统角色id，1财务，4优惠券
	 */
	private Integer roleId;

	/**
	 * 0无效，1有效
	 */
	private Integer status;

	/**
	 * 修改时间
	 */
	private Date modifyTime;

	/**
	 * 头像
	 */
	private String imgUrl;

	/**
	 * 登录cookie
	 */
	private String cookie;

	/**
	 * 部门
	 */
	private Integer departId;

	/**
	 * 诊室id
	 */
	private Integer docroomId;

	/**
	 * 
	 */
	private BigDecimal discRate;

	/**
	 * 1显示 0不显示
	 */
	private Integer isShow;

	/**
	 * 职称
	 */
	private String title;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(Integer unitCode) {
		this.unitCode = unitCode;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getIslogin() {
		return islogin;
	}

	public void setIslogin(Integer islogin) {
		this.islogin = islogin;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastvistTime() {
		return lastvistTime;
	}

	public void setLastvistTime(Date lastvistTime) {
		this.lastvistTime = lastvistTime;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public Integer getDepartId() {
		return departId;
	}

	public void setDepartId(Integer departId) {
		this.departId = departId;
	}

	public Integer getDocroomId() {
		return docroomId;
	}

	public void setDocroomId(Integer docroomId) {
		this.docroomId = docroomId;
	}

	public BigDecimal getDiscRate() {
		return discRate;
	}

	public void setDiscRate(BigDecimal discRate) {
		this.discRate = discRate;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}