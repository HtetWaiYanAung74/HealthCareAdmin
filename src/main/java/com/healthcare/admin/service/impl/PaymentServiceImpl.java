package com.healthcare.admin.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.admin.dao.PaymentDAO;
import com.healthcare.admin.domain.Payment;
import com.healthcare.admin.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentDAO paymentDAO;
	
	@Override
	public Payment findById(Long id) {
		// TODO Auto-generated method stub
		return paymentDAO.findById(id).get();
	}

	@Override
	public Payment save(Payment payment) {
		// TODO Auto-generated method stub
		return paymentDAO.save(payment);
	}

	@Override
	public void deleteById(Long paymentId) {
		// TODO Auto-generated method stub
		paymentDAO.deleteById(paymentId);
	}

}
