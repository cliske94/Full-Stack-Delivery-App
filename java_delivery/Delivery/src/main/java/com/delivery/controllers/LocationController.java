package com.delivery.controllers;

import java.util.List;

import com.delivery.models.Location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
