package com.healthcare.admin.dao;

import org.springframework.data.repository.CrudRepository;

import com.healthcare.admin.domain.Treatment;

public interface TreatmentDAO extends CrudRepository<Treatment, Long>{
	
	

}
