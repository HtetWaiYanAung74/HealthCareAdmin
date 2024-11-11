package com.healthcare.admin.service;

import com.healthcare.admin.domain.Treatment;

public interface TreatmentService {
	
	Treatment findById(Long treatmentId);
	
	Treatment save(Treatment treatment);
	
	void remove(Long treatmentId);
	
	

}
