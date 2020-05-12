package com.delivery.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.delivery.models.Faculty;
import com.delivery.repositories.FacultyRepository;

@RestController
@RequestMapping("/api")
public class FacultyController {

	@Autowired
    FacultyRepository dao;
	
	@GetMapping("/faculty")
    public List<Faculty> getFaculty() {
        List<Faculty> foundFaculty = dao.findAll();
        return foundFaculty;
    }
	
	@GetMapping("/faculty/{persons_id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable(value="persons_id") Integer id) {
        Faculty foundFaculty = dao.findById(id).orElse(null);

        if(foundFaculty == null) {
            return ResponseEntity.notFound().header("Faculty","Nothing found with that id").build();
        }
        return ResponseEntity.ok(foundFaculty);
    }
}