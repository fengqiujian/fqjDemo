package com.enjoyhis.pojo;

import com.enjoyhis.persistence.his.po.HisPatient;
import com.enjoyhis.persistence.his.po.HisRegister;

/**
 * 挂号、预约 pojo
 * 
 * @author tianfei
 *
 */
public class Register extends HisRegister {

	private static final long serialVersionUID = 1L;
	private String bookingDate;// 预约日期
	private String begin;// 预约开始时间
	private String end;// 预约结束时间
	private String maindocName;// 医生姓名

	private HisPatient patient;// 患者

	public HisPatient getPatient() {
		return patient;
	}

	public void setPatient(HisPatient patient) {
		this.patient = patient;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getMaindocName() {
		return maindocName;
	}

	public void setMaindocName(String maindocName) {
		this.maindocName = maindocName;
	}

	@Override
	public String toString() {
		return "Register [bookingDate=" + bookingDate + ", begin=" + begin + ", end=" + end + ", maindocName=" + maindocName + ", patient=" + patient + "]";
	}

}
