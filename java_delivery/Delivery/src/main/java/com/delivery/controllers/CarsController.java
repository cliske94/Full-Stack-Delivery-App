package com.delivery.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.delivery.models.Car;
import com.delivery.repositories.CarsRepository;

@RestController
@RequestMapping("/api")
public class CarsController {

	@Autowired
    CarsRepository dao;
	
	@GetMapping("/cars")
    public List<Car> getCars() {
        List<Car> foundCars = dao.findAll();
        return foundCars;
    }
	
	@GetMapping("/cars/{VIN}")
    public ResponseEntity<Car> getCar(@PathVariable(value="VIN") String id) {
        Car foundCar = dao.findById(id).orElse(null);

        if(foundCar == null) {
            return ResponseEntity.notFound().header("Car","Nothing found with that VIN").build();
        }
        return ResponseEntity.ok(foundCar);
    }
	
	@PostMapping("/cars")
    public ResponseEntity<Car> postCar(@RequestBody Car car) {

        // Saving to DB using an instance of the repo interface.
        Car createdCar = dao.save(car);

        // RespEntity crafts response to include correct status codes.
        return ResponseEntity.ok(createdCar);
    }
}
