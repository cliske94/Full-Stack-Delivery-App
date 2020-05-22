package com.delivery.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
