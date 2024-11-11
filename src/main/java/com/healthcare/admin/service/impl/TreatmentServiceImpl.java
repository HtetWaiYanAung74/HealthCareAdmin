package com.healthcare.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.healthcare.admin.dao.TreatmentDAO;
import com.healthcare.admin.domain.Treatment;
import com.healthcare.admin.service.TreatmentService;

@Service
public class TreatmentServiceImpl implements TreatmentService{
	
	@Autowired
	private TreatmentDAO treatmentDAO;

	@Override
	public Treatment findById(Long treatmentId) {
		// TODO Auto-generated method stub
		return treatmentDAO.findById(treatmentId).get();
	}

	@Override
	public Treatment save(Treatment treatment) {
		// TODO Auto-generated method stub
		return treatmentDAO.save(treatment);
	}

	@Override
	public void remove(Long treatmentId) {
		// TODO Auto-generated method stub
		treatmentDAO.deleteById(treatmentId);
	}

	

}
