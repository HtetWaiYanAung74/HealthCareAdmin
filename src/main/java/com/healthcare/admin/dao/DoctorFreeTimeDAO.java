package com.healthcare.admin.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.healthcare.admin.domain.DoctorFreeTime;

public interface DoctorFreeTimeDAO extends CrudRepository<DoctorFreeTime,Long>{

	List<DoctorFreeTime> findAll();
	
	
}
