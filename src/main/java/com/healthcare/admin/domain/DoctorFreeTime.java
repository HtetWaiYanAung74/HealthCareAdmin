package com.healthcare.admin.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DoctorFreeTime {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long doctorFreeTimeId;
	
	private String startTime;
	private String endTime;
	private String day;
	private Integer numberOfPatient;
	
	@ManyToOne
	@JoinColumn(name = "doctorId")
	private Doctor doctor;
	
	public DoctorFreeTime() {}

	public Long getDoctorFreeTimeId() {
		return doctorFreeTimeId;
	}

	public void setDoctorFreeTimeId(Long doctorFreeTimeId) {
		this.doctorFreeTimeId = doctorFreeTimeId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Integer getNumberOfPatient() {
		return numberOfPatient;
	}

	public void setNumberOfPatient(Integer numberOfPatient) {
		this.numberOfPatient = numberOfPatient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	@Override
	public String toString() {
		return "DoctorFreeTime [doctorFreeTimeId=" + doctorFreeTimeId + ", startTime=" + startTime + ", endTime="
				+ endTime + ", day=" + day + ", numberOfPatient=" + numberOfPatient + ", doctor=" + doctor + "]";
	}
	
	

}
