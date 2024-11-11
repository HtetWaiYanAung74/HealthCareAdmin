package com.healthcare.admin.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PatientRecord {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long patientRecordId;
	
	private String admissionDate;
	private String dischargeDate;
	private Double totalAmount;
	
	@ManyToOne
	@JoinColumn(name = "doctorId")
	private Doctor doctor;
	
	@ManyToOne
	@JoinColumn(name = "patientId")
	private Patient patient;
	
	public PatientRecord() {}

	public Long getPatientRecordId() {
		return patientRecordId;
	}

	public void setPatientRecordId(Long patientRecordId) {
		this.patientRecordId = patientRecordId;
	}

	public String getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}

	public String getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public String toString() {
		return "PatientRecord [patientRecordId=" + patientRecordId + ", admissionDate=" + admissionDate
				+ ", dischargeDate=" + dischargeDate + ", totalAmount=" + totalAmount + ", doctor=" + doctor
				+ ", patient=" + patient + "]";
	}
	
	

}
