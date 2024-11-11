package com.healthcare.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.admin.dao.DoctorFreeTimeDAO;
import com.healthcare.admin.domain.DoctorFreeTime;
import com.healthcare.admin.service.DoctorFreeTimeService;

@Service
public class DoctorFreeTimeServiceImpl implements DoctorFreeTimeService{

	@Autowired
	private DoctorFreeTimeDAO doctorFreeTimeDAO;
	@Override
	public DoctorFreeTime save(DoctorFreeTime doctorFreeTime) {
		// TODO Auto-generated method stub
		return doctorFreeTimeDAO.save(doctorFreeTime);
	}

	@Override
	public List<DoctorFreeTime> findAll() {
		// TODO Auto-generated method stub
		return doctorFreeTimeDAO.findAll();
	}

	@Override
	public DoctorFreeTime findById(Long id) {
		// TODO Auto-generated method stub
		return doctorFreeTimeDAO.findById(id).get();
	}

	@Override
	public void removeOne(Long id) {
		// TODO Auto-generated method stub
		doctorFreeTimeDAO.deleteById(id);
		
	}

	@Override
	public void delete(DoctorFreeTime doctorFreeTime) {
		// TODO Auto-generated method stub
		doctorFreeTimeDAO.delete(doctorFreeTime);
	}

}
