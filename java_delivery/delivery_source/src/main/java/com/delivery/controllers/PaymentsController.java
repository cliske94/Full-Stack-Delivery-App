package com.delivery.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping("/payments")
	public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
		Payment createdPayment = dao.save(payment);
		return ResponseEntity.ok(createdPayment);
	}
	
	@DeleteMapping("/payments/{payment_id}")
	public ResponseEntity<Payment> deletePayment(@PathVariable(value="payment_id") Integer id) {
		Payment foundPayment = dao.findById(id).orElse(null);
		if (foundPayment == null) return ResponseEntity.notFound().header("Payment", "Nothing found with that id").build();
		else dao.delete(foundPayment);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/payments/{payment_id}")
	public ResponseEntity<Payment> updatePayment(@RequestBody Payment payment, @PathVariable(value="payment_id") Integer id) {
		Payment foundPayment = dao.findById(id).orElse(null);
		if (foundPayment == null) return ResponseEntity.notFound().header("Payment", "Nothing found with that id").build();
		else {
			foundPayment.setAmount(payment.getAmount());
			foundPayment.setCard_number(payment.getCard_number());
			foundPayment.setCvv(payment.getCvv());
			foundPayment.setExp_date(payment.getExp_date());
			foundPayment.setPayment_type(payment.getPayment_type());
			foundPayment.setZipcode(payment.getZipcode());
			dao.save(foundPayment);
		}
		return ResponseEntity.ok().header("Payment", "Nothing found with that id").build();
	}
}
