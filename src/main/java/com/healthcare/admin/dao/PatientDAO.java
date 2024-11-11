package com.healthcare.admin.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.healthcare.admin.domain.Patient;

public interface PatientDAO extends CrudRepository<Patient, Long>{
	
	List<Patient> findByfirstNameAndLastName(String firstName, String lastName);
	
	List<Patient> findByType(String type);
	
	List<Patient> findAll();
	
	Patient findByNrc(String nrc);
	
	Patient findByPhone(String phone);

}
