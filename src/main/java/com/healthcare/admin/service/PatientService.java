package com.healthcare.admin.service;

import java.util.List;

import com.healthcare.admin.domain.Patient;

public interface PatientService {
	
	List<Patient> findByFirstNameAndLastName(String firstName, String lastName);
	
	List<Patient> findByType(String type);
	
	List<Patient> findAll();
	
	Patient findById(Long id);
	
	Patient save(Patient patient);
	
	Patient findByNRC(String nrc);

	Patient findByPhone(String phone);
	
	void deleteById(Long patientId);

}
