package com.healthcare.admin.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.healthcare.admin.domain.Doctor;

public interface DoctorDAO extends CrudRepository<Doctor,Long>{
	List<Doctor> findAll();
	
	Doctor findByPhone(String phone);
	
	Doctor findByNrc(String nrc);
	

}
