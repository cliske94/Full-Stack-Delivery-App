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

import com.delivery.repositories.StaffRepository;
import com.delivery.models.Staff;

@RestController
@RequestMapping("/api")
public class StaffController {

	@Autowired
	StaffRepository dao;
	
	@GetMapping("/staff")
	public List<Staff> getStaff()
	{
		List<Staff> foundStaff = dao.findAll();
		return foundStaff;
	}
	
	@GetMapping("/staff/{staff_id}")
	public ResponseEntity<Staff> getStaff(@PathVariable(value="staff_id") Integer id)
	{
		Staff staff = dao.findById(id).orElse(null);
		if(staff == null)
			return ResponseEntity.notFound().header("Staff", "Nothing found with that id").build();
		return ResponseEntity.ok(staff);
	}
	
	@PostMapping("/staff")
	public ResponseEntity<Staff> postStaff(@RequestBody Staff staff) 
	{
		Staff createdStaff = dao.save(staff);
		return ResponseEntity.ok(createdStaff);
	}
	
	@DeleteMapping("/staff/{staff_id}")
	public ResponseEntity<Staff> deleteStaff(@PathVariable(value="staff_id") Integer id)
	{
		Staff staffMember = dao.findById(id).orElse(null);
		if(staffMember == null)
			return ResponseEntity.notFound().header("Staff", "Nothing found with that id").build();
		else
			dao.delete(staffMember);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("staff/{staff_id}")
	public ResponseEntity<Staff> updateStaff(@RequestBody Staff staff, @PathVariable(value="staff_id") Integer id)
	{
		Staff foundStaff = dao.findById(id).orElse(null);
		
		if (foundStaff == null)
			return ResponseEntity.notFound().header("Staff", "Nothing found with that id").build();
		else
		{
			foundStaff.setAdminYorN(staff.getAdminYorN());
			//foundStaff.setPersons_id(staff.getPersons_id());
			foundStaff.setPosition(staff.getPosition());
			//foundStaff.setStaff_id(staff.getStaff_id());
			dao.save(foundStaff);
		}
		return ResponseEntity.ok().header("Staff", "Properly updated Staff record").build();
	}
}
