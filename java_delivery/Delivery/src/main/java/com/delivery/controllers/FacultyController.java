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
	
	@PostMapping("/faculty")
	public ResponseEntity<Faculty> postFaculty(@RequestBody Faculty faculty) {
		Faculty createdFaculty = dao.save(faculty);
		return ResponseEntity.ok(createdFaculty);
	}
	
	@DeleteMapping("/faculty/{persons_id}")
	public ResponseEntity<Faculty> deleteFaculty(@PathVariable(value="persons_id") Integer id) {
		Faculty foundFaculty = dao.findById(id).orElse(null);
		if (foundFaculty == null) {
			return ResponseEntity.notFound().header("Faculty", "Nothing found with that id").build();
		} else {
			dao.delete(foundFaculty);
		}
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/faculty/{persons_id}")
	public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty, @PathVariable(value="persons_id") Integer id) {
		Faculty foundFaculty = dao.findById(id).orElse(null);
		if (foundFaculty == null) return ResponseEntity.notFound().header("Faculty", "Nothing found with that id").build();
		else {
			foundFaculty.setDegree_college(faculty.getDegree_college());
			foundFaculty.setHighest_degree(faculty.getHighest_degree());
			foundFaculty.setTitle(faculty.getTitle());
			dao.save(foundFaculty);
		}
		return ResponseEntity.ok().header("Faculty", "Properly updated faculty record").build();
	}
}