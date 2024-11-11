package com.healthcare.admin.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PatientAppointment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long appointmentId;

	private String day;

	@ManyToOne
	@JoinColumn(name = "patientId")
	private Patient patient;

	@ManyToOne
	@JoinColumn(name = "doctorId")
	private Doctor doctor;

	public PatientAppointment() {
	};

	public Long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

}
