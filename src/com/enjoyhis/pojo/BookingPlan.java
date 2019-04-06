package com.enjoyhis.pojo;

import java.util.List;

public class BookingPlan {

	List<BookingDoctor> doctors;
	List<BookingPatient> patients;

	public List<BookingDoctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<BookingDoctor> doctors) {
		this.doctors = doctors;
	}

	public List<BookingPatient> getPatients() {
		return patients;
	}

	public void setPatients(List<BookingPatient> patients) {
		this.patients = patients;
	}

}