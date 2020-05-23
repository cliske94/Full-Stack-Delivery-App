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

import com.delivery.models.Student;
import com.delivery.repositories.StudentsRepository;

@RestController
@RequestMapping("/api")
public class StudentsController {

	@Autowired
	StudentsRepository dao;
	
	@GetMapping("/students")
	public List<Student> getStudents()
	{
		List<Student> foundStudents = dao.findAll();
		return foundStudents;
	}
	
	@GetMapping("/students/{Persons_id}")
	public ResponseEntity<Student> getStudent(@PathVariable(value="Persons_id") Integer id)
	{
		Student student = dao.findById(id).orElse(null);
		if(student == null)
			return ResponseEntity.notFound().header("Student", "Nothing found with that id").build();
		return ResponseEntity.ok(student);
	}
	
	@PostMapping("/students")
	public ResponseEntity<Student> postStudent(@RequestBody Student student)
	{
		Student createdStudent = dao.save(student);
		return ResponseEntity.ok(createdStudent);
	}
	
	@DeleteMapping("/students/{Persons_id}")
	public ResponseEntity<Student> deleteStudent(@PathVariable(value="Persons_id") Integer id)
	{
		Student student = dao.findById(id).orElse(null);
		if(student == null)
			return ResponseEntity.notFound().header("Student", "Nothing found with that id").build();
		else
			dao.delete(student);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/students/{Persons_id}")
	public ResponseEntity<Student> updatedStudent(@RequestBody Student student, @PathVariable(value="Persons_id") Integer id)
	{
		Student foundStudent = dao.findById(id).orElse(null);
		if(foundStudent == null)
			return ResponseEntity.notFound().header("Student", "Nothing found with that id").build();
		else
		{
			foundStudent.setGrad_year(student.getGrad_year());
			foundStudent.setMajor(student.getMajor());
			foundStudent.setProgram_type(student.getProgram_type());
			foundStudent.setStudent_id(student.getStudent_id());
			dao.save(foundStudent);
		}
		return ResponseEntity.ok().header("Student", "Properly updated Student record").build();
	}
}
