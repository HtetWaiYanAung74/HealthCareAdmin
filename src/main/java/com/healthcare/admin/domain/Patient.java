package com.healthcare.admin.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long patientId;

	private String firstName;
	private String lastName;
	private String phone;
	private String address;
	private String type;
	private String dob;

	@Column(columnDefinition = "text")
	private String symptoms;

	private String bloodType;
	private String gender;
	private String nrc;
	private Integer dateCount;
	
	private String admissionDate;
	private String dischargeDate;
	
	private Boolean isDeleteable = false;

	@OneToMany(mappedBy = "patient")
	@JsonIgnore
	private List<PatientAppointment> patientAppointmentList;

	@OneToMany(mappedBy = "patient")
	@JsonIgnore
	private List<Treatment> treatmentList;

	@OneToMany(mappedBy = "patient")
	@JsonIgnore
	private List<PatientRecord> patientRecordList;

	@ManyToOne
	private RoomOrWard roomOrWard;

	@OneToOne(cascade = CascadeType.ALL ,mappedBy = "patient")
	private User user;

	@OneToMany(mappedBy = "patient")
	@JsonIgnore
	private List<Payment> paymentList;

	public Patient() {
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNrc() {
		return nrc;
	}

	public void setNrc(String nrc) {
		this.nrc = nrc;
	}

	public List<PatientAppointment> getPatientAppointmentList() {
		return patientAppointmentList;
	}

	public void setPatientAppointmentList(List<PatientAppointment> patientAppointmentList) {
		this.patientAppointmentList = patientAppointmentList;
	}

	public List<Treatment> getTreatmentList() {
		return treatmentList;
	}

	public void setTreatmentList(List<Treatment> treatmentList) {
		this.treatmentList = treatmentList;
	}

	public List<PatientRecord> getPatientRecordList() {
		return patientRecordList;
	}

	public void setPatientRecordList(List<PatientRecord> patientRecordList) {
		this.patientRecordList = patientRecordList;
	}

	public RoomOrWard getRoomOrWard() {
		return roomOrWard;
	}

	public void setRoomOrWard(RoomOrWard roomOrWard) {
		this.roomOrWard = roomOrWard;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Payment> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List<Payment> paymentList) {
		this.paymentList = paymentList;
	}

	public Integer getDateCount() {
		return dateCount;
	}

	public void setDateCount(Integer dateCount) {
		this.dateCount = dateCount;
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

	public Boolean getIsDeleteable() {
		return isDeleteable;
	}

	public void setIsDeleteable(Boolean isDeleteable) {
		this.isDeleteable = isDeleteable;
	}
	
}
