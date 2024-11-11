package com.healthcare.admin.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long doctorId;

	private String firstName;
	private String lastName;
	private String phone;
	private String address;
	private String specialization;
	private String doctorProfile;
	private String dob;
	private String nrc;
	private String gender;
	private String imageName;
	
	@Transient
	private MultipartFile doctorImage;

	@OneToMany(mappedBy = "doctor")
	@JsonIgnore
	private List<Treatment> treatmentList;

	@OneToMany(mappedBy = "doctor")
	@JsonIgnore
	private List<PatientRecord> patientRecord;

	@OneToMany(mappedBy = "doctor")
	@JsonIgnore
	private List<DoctorFreeTime> doctorFreeTime;
	
	@OneToMany(mappedBy = "doctor")
	@JsonIgnore
	private List<PatientAppointment> patientAppointmentList;
	
	public Doctor() {
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
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

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public MultipartFile getDoctorImage() {
		return doctorImage;
	}

	public void setDoctorImage(MultipartFile doctorImage) {
		this.doctorImage = doctorImage;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getNrc() {
		return nrc;
	}

	public void setNrc(String nrc) {
		this.nrc = nrc;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public List<Treatment> getTreatmentList() {
		return treatmentList;
	}

	public void setTreatmentList(List<Treatment> treatmentList) {
		this.treatmentList = treatmentList;
	}

	public List<PatientRecord> getPatientRecord() {
		return patientRecord;
	}

	public void setPatientRecord(List<PatientRecord> patientRecord) {
		this.patientRecord = patientRecord;
	}

	public List<DoctorFreeTime> getDoctorFreeTime() {
		return doctorFreeTime;
	}

	public void setDoctorFreeTime(List<DoctorFreeTime> doctorFreeTime) {
		this.doctorFreeTime = doctorFreeTime;
	}

	public List<PatientAppointment> getPatientAppointmentList() {
		return patientAppointmentList;
	}

	public void setPatientAppointmentList(List<PatientAppointment> patientAppointmentList) {
		this.patientAppointmentList = patientAppointmentList;
	}

	public String getDoctorProfile() {
		return doctorProfile;
	}

	public void setDoctorProfile(String doctorProfile) {
		this.doctorProfile = doctorProfile;
	}

}
