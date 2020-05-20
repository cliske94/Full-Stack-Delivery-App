package com.delivery.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.models.Payment;
import com.delivery.repositories.PaymentsRepository;

@RestController
@RequestMapping("/api")
public class PaymentsController {

	@Autowired
	PaymentsRepository dao;
	
	@GetMapping("/payments")
	public List<Payment> getPayments()
	{
		List<Payment> foundPayments = dao.findAll();
		return foundPayments;
	}
	
	@GetMapping("/payments/{payment_id}")
	public ResponseEntity<Payment> getPayment(@PathVariable(value="payment_id") Integer id)
	{
		Payment foundPayment = dao.findById(id).orElse(null);
		if (foundPayment == null) return ResponseEntity.notFound().header("Payment", "Nothing found with that id").build();
		return ResponseEntity.ok(foundPayment);
	}
}
