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

import com.delivery.models.Person;
import com.delivery.repositories.PersonsRepository;

@RestController
@RequestMapping("/api")
public class PersonsController {

	@Autowired
	PersonsRepository dao;
	
	@GetMapping("/persons")
	public List<Person> getPersons()
{
		List<Person> foundPersons = dao.findAll();
		return foundPersons;
	}
	
	@GetMapping("/persons/{id}")
	public ResponseEntity<Person> getPerson(@PathVariable(value="id") Integer id)
	{
		Person foundPerson = dao.findById(id).orElse(null);
		if(foundPerson == null) return ResponseEntity.notFound().header("Person", "Nothing found with that id").build();
		return ResponseEntity.ok(foundPerson);
	}
	
	@PostMapping("/persons")
	public ResponseEntity<Person> postPerson(@RequestBody Person person) {
		Person foundPerson = dao.save(person);
		return ResponseEntity.ok(foundPerson);
	}
	
	@DeleteMapping("/persons/{id}")
	public ResponseEntity<Person> deletePerson(@PathVariable(value="id") Integer id) {
		Person foundPerson = dao.findById(id).orElse(null);
		if (foundPerson == null) return ResponseEntity.notFound().header("Person", "Nothing found with that id").build();
		else dao.delete(foundPerson);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/persons/{id}")
	public ResponseEntity<Person> updatePerson(@RequestBody Person person, @PathVariable(value="id") Integer id) {
		Person foundPerson = dao.findById(id).orElse(null);
		if (foundPerson == null) return ResponseEntity.notFound().header("Person", "Nothing found with that id").build();
		else {
			foundPerson.setEmail(person.getEmail());
			foundPerson.setFirst_name(person.getFirst_name());
			foundPerson.setLast_name(person.getLast_name());
			foundPerson.setMailing_address(person.getMailing_address());
			foundPerson.setPassword(person.getPassword());
			foundPerson.setPhone_number(person.getPhone_number());
			dao.save(foundPerson);
		}
		return ResponseEntity.ok().header("Person", "Properly updated person record").build();
	}
}
