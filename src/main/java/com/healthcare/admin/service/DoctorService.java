package com.healthcare.admin.service;

import java.util.List;

import com.healthcare.admin.domain.Doctor;
import com.healthcare.admin.domain.DoctorFreeTime;



public interface DoctorService {

	Doctor save(Doctor doctor);
	
	List<Doctor> findAll();
	
	Doctor findById(Long id);
	
	void removeOne(Long id);
	
	void updateDoctorFreeTime(Doctor doctor,DoctorFreeTime doctorFreeTime);
	
	Doctor findByPhone(String phone);
	
	Doctor findByNrc(String nrc);
}

