package com.healthcare.admin.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.healthcare.admin.domain.Patient;
import com.healthcare.admin.domain.Payment;

public interface PaymentDAO extends CrudRepository<Payment, Long> {
	
	List<Payment> findByPatient(Patient patient);

}
