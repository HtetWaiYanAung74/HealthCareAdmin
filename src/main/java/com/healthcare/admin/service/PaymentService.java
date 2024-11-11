package com.healthcare.admin.service;

import com.healthcare.admin.domain.Payment;

public interface PaymentService {
	
	Payment findById(Long id);
	
	Payment save(Payment payment);	
	
	void deleteById(Long paymentId);
	
}
