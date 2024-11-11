package com.healthcare.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.admin.dao.DoctorDAO;
import com.healthcare.admin.domain.Doctor;
import com.healthcare.admin.domain.DoctorFreeTime;
import com.healthcare.admin.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService{

	@Autowired
	private DoctorDAO doctorDAO;
	@Override
	public Doctor save(Doctor doctor) {
		// TODO Auto-generated method stub
		return doctorDAO.save(doctor);
	}

	@Override
	public List<Doctor> findAll() {
		// TODO Auto-generated method stub
		return doctorDAO.findAll();
	}

	@Override
	public Doctor findById(Long id) {
		// TODO Auto-generated method stub
		return doctorDAO.findById(id).get();
	}

	@Override
	public void removeOne(Long id) {
		// TODO Auto-generated method stub
		doctorDAO.deleteById(id);
		
	}

	@Override
	public void updateDoctorFreeTime(Doctor doctor, DoctorFreeTime doctorFreeTime) {
		// TODO Auto-generated method stub
		doctorFreeTime.setDoctor(doctor);
		doctor.getDoctorFreeTime().add(doctorFreeTime);
		doctorDAO.save(doctor);
		
	}

	@Override
	public Doctor findByPhone(String phone) {
		// TODO Auto-generated method stub
		return doctorDAO.findByPhone(phone);
	}

	@Override
	public Doctor findByNrc(String nrc) {
		// TODO Auto-generated method stub
		return doctorDAO.findByNrc(nrc);
	}

	
	

}
