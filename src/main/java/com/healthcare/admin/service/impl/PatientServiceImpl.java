package com.healthcare.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.admin.dao.PatientDAO;
import com.healthcare.admin.domain.Patient;
import com.healthcare.admin.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService{

	@Autowired
	private PatientDAO patientDAO;
	
	@Override
	public List<Patient> findByFirstNameAndLastName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return patientDAO.findByfirstNameAndLastName(firstName, lastName);
	}

	@Override
	public List<Patient> findByType(String type) {
		// TODO Auto-generated method stub
		return patientDAO.findByType(type);
	}

	@Override
	public Patient findById(Long id) {
		// TODO Auto-generated method stub
		return patientDAO.findById(id).get();
	}

	@Override
	public Patient save(Patient patient) {
		// TODO Auto-generated method stub
		return patientDAO.save(patient);
	}

	@Override
	public void deleteById(Long patientId) {
		// TODO Auto-generated method stub
		patientDAO.deleteById(patientId);
	}

	@Override
	public Patient findByNRC(String nrc) {
		// TODO Auto-generated method stub
		return patientDAO.findByNrc(nrc);
	}

	@Override
	public Patient findByPhone(String phone) {
		// TODO Auto-generated method stub
		return patientDAO.findByPhone(phone);
	}

	@Override
	public List<Patient> findAll() {
		// TODO Auto-generated method stub
		return patientDAO.findAll();
	}

}
