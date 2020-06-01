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
	
	@PostMapping("/payroll")
	public ResponseEntity<Payroll> postPayroll(@RequestBody Payroll payroll) {
		Payroll createdPayroll = dao.save(payroll);
		return ResponseEntity.ok(createdPayroll);
	}
	
	@DeleteMapping("/payroll/{transaction_id}")
	public ResponseEntity<Payroll> deletePayroll(@PathVariable(value="transaction_id") Integer id) {
		Payroll foundPayroll = dao.findById(id).orElse(null);
		if (foundPayroll == null) return ResponseEntity.notFound().header("Payroll", "Nothing found with thatt id").build();
		else dao.delete(foundPayroll);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/payroll/{transaction_id}")
	public ResponseEntity<Payroll> updatePayroll(@RequestBody Payroll payroll, @PathVariable(value="transaction_id") Integer id) {
		Payroll foundPayroll = dao.findById(id).orElse(null);
		if (foundPayroll == null) return ResponseEntity.notFound().header("Payroll", "Nothing found with that id").build();
		else {
			foundPayroll.setGross_pay(payroll.getGross_pay());
			foundPayroll.setHours_worked(payroll.getHours_worked());
			foundPayroll.setTaxes_taken(payroll.getTaxes_taken());
			foundPayroll.setTimestamp(payroll.getTimestamp());
			foundPayroll.setYTD_pay(payroll.getYTD_pay());
			dao.save(foundPayroll);
		}
		return ResponseEntity.ok().header("Payroll", "Properly updated payroll record").build();
	}
}
