package com.delivery.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.models.Payroll;
import com.delivery.repositories.PayrollsRepository;

@RestController
@RequestMapping("/api")
public class PayrollsController {

	@Autowired
	PayrollsRepository dao;
	
	@GetMapping("/payroll")
	public List<Payroll> getPayrolls()
	{
		List<Payroll> foundPayroll = dao.findAll();
		return foundPayroll;
	}
	
	@GetMapping("/payroll/{transaction_id}")
	public ResponseEntity<Payroll> getPayroll(@PathVariable(value="transaction_id") Integer id)
	{
		Payroll foundPayroll = dao.findById(id).orElse(null);
		if(foundPayroll == null) return ResponseEntity.notFound().header("Payroll", "Nothing found with that id").build();
		return ResponseEntity.ok(foundPayroll);
	}
}
