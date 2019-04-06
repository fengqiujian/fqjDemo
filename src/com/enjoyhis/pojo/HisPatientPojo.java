package com.enjoyhis.pojo;

import com.enjoyhis.persistence.his.po.HisPatient;

public class HisPatientPojo extends HisPatient {
	private static final long serialVersionUID = 1L;
	private String docName;
	private String fmtBirthday;
	private String fmtlastappointDate;
	private String fmtlasthospDate;
	private String fmtNewlyDate;

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getFmtBirthday() {
		return fmtBirthday;
	}

	public void setFmtBirthday(String fmtBirthday) {
		this.fmtBirthday = fmtBirthday;
	}

	public String getFmtlastappointDate() {
		return fmtlastappointDate;
	}

	public void setFmtlastappointDate(String fmtlastappointDate) {
		this.fmtlastappointDate = fmtlastappointDate;
	}

	public String getFmtlasthospDate() {
		return fmtlasthospDate;
	}

	public void setFmtlasthospDate(String fmtlasthospDate) {
		this.fmtlasthospDate = fmtlasthospDate;
	}

	public String getFmtNewlyDate() {
		return fmtNewlyDate;
	}

	public void setFmtNewlyDate(String fmtNewlyDate) {
		this.fmtNewlyDate = fmtNewlyDate;
	}
}