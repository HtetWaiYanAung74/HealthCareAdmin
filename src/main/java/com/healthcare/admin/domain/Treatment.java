package com.healthcare.admin.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Treatment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long treatmentId;
	private String treatmentDate;
	private Integer bloodPressure;
	
	@Column(columnDefinition = "text")
	private String comment;
	
	private String recordDate;
	
	@ManyToOne
	@JoinColumn(name = "doctorId")
	private Doctor doctor;
	
	@ManyToOne
	@JoinColumn(name = "patientId")
	private Patient patient;
	
	public Treatment() {}

	public Long getTreatmentId() {
		return treatmentId;
	}

	public void setTreatmentId(Long treatmentId) {
		this.treatmentId = treatmentId;
	}

	public String getTreatmentDate() {
		return treatmentDate;
	}

	public void setTreatmentDate(String treatmentDate) {
		this.treatmentDate = treatmentDate;
	}

	public Integer getBloodPressure() {
		return bloodPressure;
	}

	public void setBloodPressure(Integer bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}


	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	

}
