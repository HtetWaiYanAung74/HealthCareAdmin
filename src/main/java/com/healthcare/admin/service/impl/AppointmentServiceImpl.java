package com.healthcare.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.admin.dao.AppointmentDAO;
import com.healthcare.admin.domain.PatientAppointment;
import com.healthcare.admin.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentDAO appointmentDAO;
	
	@Override
	public List<PatientAppointment> findAll() {
		// TODO Auto-generated method stub
		return appointmentDAO.findAll();
	}

}
