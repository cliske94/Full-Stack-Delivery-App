package com.delivery.controllers;

import java.util.List;

import com.delivery.models.Location;

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

import com.delivery.repositories.LocationsRepository;

@RestController
@RequestMapping("/api")
public class LocationController {

	@Autowired
	LocationsRepository dao;
	
	@GetMapping("/locations")
	public List<Location> getLocations()
	{
		List<Location> foundLocations = dao.findAll();
		return foundLocations;
	}
	
	@GetMapping("/locations/{id}")
	public ResponseEntity<Location> getLocation(@PathVariable(value="id") Integer id)
	{
		Location foundLocation = dao.findById(id).orElse(null);
		
		if(foundLocation.equals(null))
		{
			return ResponseEntity.notFound().header("Location","Nothing found with that id").build();
		}
		return ResponseEntity.ok(foundLocation);
	}
	
	@PostMapping("/locations")
	public ResponseEntity<Location> postLocation(@RequestBody Location location) {
		Location createdLocation = dao.save(location);
		return ResponseEntity.ok(createdLocation);
	}
	
	@DeleteMapping("/locations/{id}")
	public ResponseEntity<Location> deleteLocation(@PathVariable(value="id") Integer id) {
		Location foundLocation = dao.findById(id).orElse(null);
		if (foundLocation == null) {
			return ResponseEntity.notFound().header("Location", "Nothing found with that id").build();
		} else dao.delete(foundLocation);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/locations/{id}")
	public ResponseEntity<Location> updateLocation(@RequestBody Location location, @PathVariable(value="id") Integer id) {
		Location foundLocation = dao.findById(id).orElse(null);
		if(foundLocation == null) {
			return ResponseEntity.notFound().header("Location", "Nothing found with that id").build();
		} else {
			foundLocation.setDropoff_description(location.getDropoff_description());
			foundLocation.setGps_coordinates(location.getGps_coordinates());
			foundLocation.setLocation_address(location.getLocation_address());
			foundLocation.setName(location.getName());
			dao.save(foundLocation);
		}
		return ResponseEntity.ok().header("Location", "Properly updated location id").build();
	}
}
