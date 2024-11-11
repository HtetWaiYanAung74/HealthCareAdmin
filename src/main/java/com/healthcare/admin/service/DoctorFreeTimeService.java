package com.healthcare.admin.service;

import java.util.List;

import com.healthcare.admin.domain.DoctorFreeTime;


public interface DoctorFreeTimeService {

	DoctorFreeTime save(DoctorFreeTime doctorFreeTime);
	
	List<DoctorFreeTime> findAll();
	
	DoctorFreeTime findById(Long id);
	
	void removeOne(Long id);

	void delete(DoctorFreeTime doctorFreeTime);
	
}
