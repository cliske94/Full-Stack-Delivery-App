package com.delivery.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.delivery.models.Driver;
import com.delivery.repositories.DriversRepository;

@RestController
@RequestMapping("/api")
public class DriversController {

	@Autowired
    DriversRepository dao;
	
	@GetMapping("/drivers")
    public List<Driver> getDrivers() {
        List<Driver> foundDrivers = dao.findAll();
        return foundDrivers;
    }
	
	@GetMapping("/drivers/{persons_id}")
    public ResponseEntity<Driver> getDrivers(@PathVariable(value="persons_id") Integer id) {
        Driver foundDriver = dao.findById(id).orElse(null);

        if(foundDriver == null) {
            return ResponseEntity.notFound().header("Driver","Nothing found with that id").build();
        }
        return ResponseEntity.ok(foundDriver);
    }
}