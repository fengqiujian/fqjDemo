package com.enjoyhis.pojo;

public class RegisterCall {

	private String uniqid;// 唯一标识
	private String departments;// 科室
	private String room;// 诊室
	private String doctor;// 医生姓名
	private String title;// 医生职称
	private String avatar;// 医生头像
	private String patient;// 患者名称
	private String pat_sex;// 患者性别(0-未知, 1-男, 2-女)
	private String pat_age;// 患者年龄
	private String number;// 就诊编号
	private String sign;// 参数签名

	public String getUniqid() {
		return uniqid;
	}

	public String getDepartments() {
		return departments;
	}

	public String getRoom() {
		return room;
	}

	public String getDoctor() {
		return doctor;
	}

	public String getTitle() {
		return title;
	}

	public String getAvatar() {
		return avatar;
	}

	public String getPatient() {
		return patient;
	}

	public String getPat_sex() {
		return pat_sex;
	}

	public String getPat_age() {
		return pat_age;
	}

	public String getNumber() {
		return number;
	}

	public String getSign() {
		return sign;
	}

	public void setUniqid(String uniqid) {
		this.uniqid = uniqid;
	}

	public void setDepartments(String departments) {
		this.departments = departments;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}

	public void setPat_sex(String pat_sex) {
		this.pat_sex = pat_sex;
	}

	public void setPat_age(String pat_age) {
		this.pat_age = pat_age;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Override
	public String toString() {
		return "RegisterCall [uniqid=" + uniqid + ", departments=" + departments + ", room=" + room + ", doctor=" + doctor + ", title=" + title + ", avatar=" + avatar + ", patient=" + patient + ", pat_sex=" + pat_sex + ", pat_age=" + pat_age + ", number=" + number + ", sign=" + sign + "]";
	}

}
