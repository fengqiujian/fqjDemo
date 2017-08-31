package com.enjoyhis.pojo;

import com.enjoyhis.persistence.his.po.HisPatientCase;

public class PatientCase extends HisPatientCase {

	private static final long serialVersionUID = 1L;

	private String maindocName;

	public String getMaindocName() {
		return maindocName;
	}

	public void setMaindocName(String maindocName) {
		this.maindocName = maindocName;
	}
}
