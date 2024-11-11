package com.healthcare.admin.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.healthcare.admin.domain.PatientAppointment;

public interface AppointmentDAO extends CrudRepository<PatientAppointment, Long> {

	List<PatientAppointment> findAll();
	
}
