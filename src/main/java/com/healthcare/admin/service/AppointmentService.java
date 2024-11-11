package com.healthcare.admin.service;

import java.util.List;

import com.healthcare.admin.domain.PatientAppointment;

public interface AppointmentService {
	
	List<PatientAppointment> findAll();

}
